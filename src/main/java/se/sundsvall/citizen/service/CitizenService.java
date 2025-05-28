package se.sundsvall.citizen.service;

import static java.lang.String.format;
import static org.zalando.problem.Status.BAD_REQUEST;
import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;
import static se.sundsvall.citizen.service.ServiceConstants.ERROR_CITIZEN_NOT_FOUND;
import static se.sundsvall.citizen.service.ServiceConstants.ERROR_PERSONAL_NUMBER_NOT_FOUND;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
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

@Service
@Transactional
public class CitizenService {

	private final CitizenRepository citizenRepository;
	private final CitizenAddressRepository citizenAddressRepository;
	private final ObjectMapper objectMapper;
	private final PartyIntegration partyIntegration;

	public CitizenService(CitizenRepository citizenRepository,
		CitizenAddressRepository citizenAddressRepository,
		ObjectMapper objectMapper, PartyIntegration partyIntegration) {
		this.citizenRepository = citizenRepository;
		this.citizenAddressRepository = citizenAddressRepository;
		this.objectMapper = objectMapper;
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
		if (!Objects.equals(municipalityId, "2281")) {
			final var citizenEntity = citizenRepository.findByPersonalNumber(personNumber)
				.orElseThrow(() -> Problem.valueOf(NOT_FOUND,
					format(ERROR_PERSONAL_NUMBER_NOT_FOUND, personNumber)));

			return citizenEntity.getPersonId();
		} else {
			// Gå mot Party
			final String type = "PRIVATE";
			return partyIntegration.getPartyId(personNumber, municipalityId, type);
		}
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
			.withPersonId(UUID.randomUUID().toString())
			.withPersonalNumber(person.getPersonalNumber());

		var savedEntity = citizenRepository.save(citizenEntity);
		return UUID.fromString(savedEntity.getPersonId());
	}
}
