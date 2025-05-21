package se.sundsvall.citizen.service.mapper;

import se.sundsvall.citizen.api.model.CitizenExtended;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;
import java.util.UUID;
import java.util.Optional;

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
}
