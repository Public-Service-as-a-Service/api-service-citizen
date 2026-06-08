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

	/**
	 * Map an inbound {@link CitizenAddress} (test-data create payload) to a new entity. The owning
	 * citizen is back-linked by the caller. addressType (e.g. POPULATION_REGISTRATION_ADDRESS),
	 * municipality and realEstateDescription are what the egensotning folkbokföringskontroll reads.
	 */
	public static CitizenAddressEntity toCitizenAddressEntity(final CitizenAddress address) {
		return Optional.ofNullable(address)
			.map(a -> CitizenAddressEntity.create()
				.withStatus(a.getStatus())
				.withRealEstateDescription(a.getRealEstateDescription())
				.withCo(a.getCo())
				.withAddress(a.getAddress())
				.withAddressArea(a.getAddressArea())
				.withAddressNumber(a.getAddressNumber())
				.withAddressLetter(a.getAddressLetter())
				.withApartmentNumber(a.getApartmentNumber())
				.withPostalCode(a.getPostalCode())
				.withCity(a.getCity())
				.withCounty(a.getCounty())
				.withMunicipality(a.getMunicipality())
				.withCountry(a.getCountry())
				.withEmigrated(a.getEmigrated())
				.withAddressType(a.getAddressType())
				.withXCoordLocal(a.getXCoordLocal())
				.withYCoordLocal(a.getYCoordLocal()))
			.orElse(null);
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
