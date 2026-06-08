package se.sundsvall.citizen.service.mapper;

import java.util.Optional;
import java.util.UUID;
import se.sundsvall.citizen.api.model.CitizenExtended;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;

public class CitizenMapper {

	private CitizenMapper() {}

	public static CitizenExtended toCitizenExtended(final CitizenEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> CitizenExtended.create()
				.withPersonId(UUID.fromString(e.getPersonId()))
				.withGivenname(e.getGivenname())
				.withLastname(e.getLastname())
				.withGender(e.getGender())
				.withCivilStatus(e.getCivilStatus())
				.withNrDate(e.getNrDate() != null ? e.getNrDate().toString() : null)
				.withPersonalNumber(e.getPersonalNumber())
				.withClassified(e.getClassified())
				.withProtectedNr(e.getProtectedNr())
				.withAddresses(CitizenAddressMapper.toCitizenAddresses(e.getAddresses())))
			.orElse(null);
	}

	/**
	 * Map an inbound {@link CitizenExtended} (test-data create payload) to a new entity. personId is
	 * left unset so it is generated on persist; addresses are mapped and back-linked to the citizen so
	 * the CascadeType.ALL relationship persists them in the same transaction.
	 */
	public static CitizenEntity toCitizenEntity(final CitizenExtended citizen) {
		return Optional.ofNullable(citizen)
			.map(c -> {
				final var entity = CitizenEntity.create()
					.withPersonalNumber(c.getPersonalNumber())
					.withGivenname(c.getGivenname())
					.withLastname(c.getLastname())
					.withGender(c.getGender())
					.withCivilStatus(c.getCivilStatus())
					.withClassified(c.getClassified())
					.withProtectedNr(c.getProtectedNr());

				Optional.ofNullable(c.getAddresses()).ifPresent(addresses -> addresses.stream()
					.map(CitizenAddressMapper::toCitizenAddressEntity)
					.forEach(addressEntity -> {
						addressEntity.setCitizen(entity);
						entity.getAddresses().add(addressEntity);
					}));

				return entity;
			})
			.orElse(null);
	}
}
