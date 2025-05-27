package se.sundsvall.citizen.service.mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import se.sundsvall.citizen.api.model.CitizenAddress;
import se.sundsvall.citizen.api.model.CitizenWithChangedAddress;
import se.sundsvall.citizen.integration.db.model.CitizenAddressEntity;

public class CitizenAddressMapper {

	private CitizenAddressMapper() {}

	public static CitizenWithChangedAddress toCitizenWithChangedAddress(CitizenAddressEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> CitizenWithChangedAddress.create()
				.withPersonId(UUID.fromString(e.getCitizen().getPersonId()))
				.withPersonNumber(e.getCitizen().getPersonalNumber())
				.withClassified(e.getCitizen().getClassified())
				.withGender(e.getCitizen().getGender())
				.withGivenname(e.getCitizen().getGivenname())
				.withLastname(e.getCitizen().getLastname())
				.withAddresses(toCitizenAddresses(e.getCitizen().getAddresses())))
			.orElse(null);
	}

	public static List<CitizenAddress> toCitizenAddresses(List<CitizenAddressEntity> entities) {
		return Optional.ofNullable(entities)
			.map(list -> list.stream()
				.map(CitizenAddressMapper::toCitizenAddress)
				.toList())
			.orElse(Collections.emptyList());
	}

	private static CitizenAddress toCitizenAddress(CitizenAddressEntity entity) {
		return Optional.ofNullable(entity)
			.map(e -> CitizenAddress.create()
				.withStatus(e.getStatus())
				.withNrDate(e.getNrDate() != null ? e.getNrDate().toString() : null)  // Convert to String
				.withRealEstateDescription(e.getRealEstateDescription())
				.withCo(e.getCo())
				.withAddress(e.getAddress())
				.withAddressArea(e.getAddressArea())
				.withAddressNumber(e.getAddressNumber())
				.withAddressLetter(e.getAddressLetter())
				.withApartmentNumber(e.getApartmentNumber())
				.withPostalCode(e.getPostalCode())
				.withCity(e.getCity())
				.withCounty(e.getCounty())
				.withMunicipality(e.getMunicipality())
				.withCountry(e.getCountry())
				.withEmigrated(e.getEmigrated())
				.withAddressType(e.getAddressType())
				.withXCoordLocal(e.getXCoordLocal())
				.withYCoordLocal(e.getYCoordLocal()))
			.orElse(null);
	}
}
