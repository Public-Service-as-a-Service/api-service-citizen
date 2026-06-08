package se.sundsvall.citizen.service;

import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;
import se.sundsvall.citizen.api.model.CitizenExtended;
import se.sundsvall.citizen.api.model.CitizenWithChangedAddress;
import se.sundsvall.citizen.api.model.ModelPostPerson;
import se.sundsvall.citizen.api.model.PersonGuidBatch;
import se.sundsvall.citizen.integration.db.CitizenAddressRepository;
import se.sundsvall.citizen.integration.db.CitizenRepository;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;
import se.sundsvall.citizen.integration.db.specification.CitizenAddressSpecification;
import se.sundsvall.citizen.integration.party.PartyIntegration;
import se.sundsvall.citizen.service.mapper.CitizenAddressMapper;
import se.sundsvall.citizen.service.mapper.CitizenMapper;
import se.sundsvall.dept44.problem.Problem;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.citizen.service.ServiceConstants.ERROR_CITIZEN_NOT_FOUND;
import static se.sundsvall.citizen.service.ServiceConstants.ERROR_PERSONAL_NUMBER_NOT_FOUND;

@Service
@Transactional
public class CitizenService {

	private final CitizenRepository citizenRepository;
	private final CitizenAddressRepository citizenAddressRepository;
	private final PartyIntegration partyIntegration;

	public CitizenService(CitizenRepository citizenRepository,
		CitizenAddressRepository citizenAddressRepository,
		PartyIntegration partyIntegration) {
		this.citizenRepository = citizenRepository;
		this.citizenAddressRepository = citizenAddressRepository;
		this.partyIntegration = partyIntegration;
	}

	public CitizenExtended getCitizenById(final UUID personId, final boolean showClassified) {
		final var citizenEntity = citizenRepository.findById(personId.toString())
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND, format(ERROR_CITIZEN_NOT_FOUND, personId)));

		if (!showClassified && citizenEntity.getClassified() != null) {
			return null;
		}

		return CitizenMapper.toCitizenExtended(citizenEntity);
	}

	public List<CitizenExtended> getCitizensByIds(List<UUID> personIds, boolean showClassified) {
		return personIds.stream()
			.map(id -> citizenRepository.findById(id.toString())
				.orElse(null))
			.filter(Objects::nonNull)
			.filter(citizen -> showClassified || citizen.getClassified() == null)
			.map(CitizenMapper::toCitizenExtended)
			.toList();
	}

	public List<CitizenWithChangedAddress> getCitizensWithChangedAddress(final OffsetDateTime changedDateFrom) {
		return citizenAddressRepository
			.findAll(CitizenAddressSpecification.hasChangedAddressSince(changedDateFrom))
			.stream()
			.map(CitizenAddressMapper::toCitizenWithChangedAddress)
			.toList();
	}

	public String getPersonalNumberById(final UUID personId) {
		return citizenRepository.findById(personId.toString())
			.map(CitizenEntity::getPersonalNumber)
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND,
				format(ERROR_CITIZEN_NOT_FOUND, personId)));
	}

	public String getPersonIdByPersonalNumber(final String personNumber, final String municipalityId) {
		// POC: the self-hosted citizen mock is authoritative. Resolve seeded/created test persons from
		// the local DB for EVERY municipality — including 2281, which upstream routes straight to the
		// real Party service. Party is kept only as a fallback for numbers never created in this mock.
		final var localMatch = citizenRepository.findByPersonalNumber(personNumber);
		if (localMatch.isPresent()) {
			return localMatch.get().getPersonId();
		}

		return partyIntegration.getPartyId(personNumber, municipalityId, "PRIVATE")
			.orElseThrow(() -> Problem.valueOf(NOT_FOUND,
				format(ERROR_PERSONAL_NUMBER_NOT_FOUND)));
	}

	public List<PersonGuidBatch> getPersonIdsInBatch(List<String> personalNumbers) {
		return personalNumbers.stream()
			.map(personalNumber -> {
				var result = PersonGuidBatch.create()
					.withPersonNumber(personalNumber);

				try {
					var citizen = citizenRepository.findByPersonalNumber(personalNumber);
					if (citizen.isPresent()) {
						result.withPersonId(UUID.fromString(citizen.get().getPersonId()))
							.withSuccess(true);
					} else {
						result.withSuccess(false)
							.withErrorMessage("Citizen not found");
					}
				} catch (Exception e) {
					result.withSuccess(false)
						.withErrorMessage("Error processing request: " + e.getMessage());
				}
				return result;
			})
			.toList();
	}

	public UUID createPerson(final ModelPostPerson person) {
		if (person == null || person.getPersonalNumber() == null || person.getPersonalNumber().isBlank()) {
			throw Problem.valueOf(BAD_REQUEST, "Personal number is required");
		}

		// Check if person already exists
		if (citizenRepository.findByPersonalNumber(person.getPersonalNumber()).isPresent()) {
			throw Problem.valueOf(CONFLICT,
				format("Person with personal number %s already exists", person.getPersonalNumber()));
		}

		final var citizenEntity = CitizenEntity.create()

			.withPersonalNumber(person.getPersonalNumber());

		var savedEntity = citizenRepository.save(citizenEntity);
		return UUID.fromString(savedEntity.getPersonId());
	}

	/**
	 * Create a fully-formed test citizen — name, civil status and (crucially) folkbokföring addresses —
	 * in a single call. Added for the egensotning POC so the frontend can seed a person that is
	 * registered (POPULATION_REGISTRATION_ADDRESS) at a given property, which the auto-approve
	 * folkbokföringskontroll requires. The generated personId is returned on the response.
	 */
	public CitizenExtended createCitizen(final CitizenExtended citizen) {
		if (citizen == null || citizen.getPersonalNumber() == null || citizen.getPersonalNumber().isBlank()) {
			throw Problem.valueOf(BAD_REQUEST, "Personal number is required");
		}

		if (citizenRepository.findByPersonalNumber(citizen.getPersonalNumber()).isPresent()) {
			throw Problem.valueOf(CONFLICT,
				format("Person with personal number %s already exists", citizen.getPersonalNumber()));
		}

		final var savedEntity = citizenRepository.save(CitizenMapper.toCitizenEntity(citizen));
		return CitizenMapper.toCitizenExtended(savedEntity);
	}
}
