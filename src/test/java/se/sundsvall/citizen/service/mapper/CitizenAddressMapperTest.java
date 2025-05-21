package se.sundsvall.citizen.service.mapper;

import org.junit.jupiter.api.Test;
import se.sundsvall.citizen.api.model.CitizenAddress;
import se.sundsvall.citizen.api.model.CitizenWithChangedAddress;
import se.sundsvall.citizen.integration.db.model.CitizenAddressEntity;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class CitizenAddressMapperTest {

	private static final OffsetDateTime CURRENT_TIME = OffsetDateTime.parse("2025-01-29T08:56:48Z");

	@Test
	void toCitizenWithChangedAddress() {
		// Arrange
		final var personId = UUID.randomUUID().toString();
		final var personalNumber = "198001011234";
		final var classified = "classified";
		final var gender = "gender";
		final var givenname = "givenname";
		final var lastname = "lastname";

		final var citizen = CitizenEntity.create()
			.withPersonId(personId)
			.withPersonalNumber(personalNumber)
			.withClassified(classified)
			.withGender(gender)
			.withGivenname(givenname)
			.withLastname(lastname);

		final var citizenAddress = CitizenAddressEntity.create()
			.withCitizen(citizen);

		// Act
		final var result = CitizenAddressMapper.toCitizenWithChangedAddress(citizenAddress);

		// Assert
		assertThat(result).isNotNull();
		assertThat(result.getPersonId()).isEqualTo(UUID.fromString(personId));
		assertThat(result.getPersonNumber()).isEqualTo(personalNumber);
		assertThat(result.getClassified()).isEqualTo(classified);
		assertThat(result.getGender()).isEqualTo(gender);
		assertThat(result.getGivenname()).isEqualTo(givenname);
		assertThat(result.getLastname()).isEqualTo(lastname);
		assertThat(result.getAddresses()).isEmpty();
	}

	@Test
	void toCitizenWithChangedAddress_Null() {
		assertThat(CitizenAddressMapper.toCitizenWithChangedAddress(null)).isNull();
	}

	@Test
	void toCitizenAddresses() {
		// Arrange
		final var status = "status";
		final var nrDate = CURRENT_TIME;
		final var realEstateDescription = "realEstateDescription";
		final var co = "co";
		final var address = "address";
		final var addressArea = "addressArea";
		final var addressNumber = "addressNumber";
		final var addressLetter = "addressLetter";
		final var apartmentNumber = "apartmentNumber";
		final var postalCode = "postalCode";
		final var city = "city";
		final var county = "county";
		final var municipality = "municipality";
		final var country = "country";
		final var emigrated = true;
		final var addressType = "addressType";
		final var xCoordLocal = 123.456;
		final var yCoordLocal = 789.012;

		final var addressEntity = CitizenAddressEntity.create()
			.withStatus(status)
			.withNrDate(nrDate)
			.withRealEstateDescription(realEstateDescription)
			.withCo(co)
			.withAddress(address)
			.withAddressArea(addressArea)
			.withAddressNumber(addressNumber)
			.withAddressLetter(addressLetter)
			.withApartmentNumber(apartmentNumber)
			.withPostalCode(postalCode)
			.withCity(city)
			.withCounty(county)
			.withMunicipality(municipality)
			.withCountry(country)
			.withEmigrated(emigrated)
			.withAddressType(addressType)
			.withXCoordLocal(xCoordLocal)
			.withYCoordLocal(yCoordLocal);

		// Act
		final var result = CitizenAddressMapper.toCitizenAddresses(List.of(addressEntity));

		// Assert
		assertThat(result)
			.hasSize(1)
			.extracting(
				CitizenAddress::getStatus,
				CitizenAddress::getNrDate,
				CitizenAddress::getRealEstateDescription,
				CitizenAddress::getCo,
				CitizenAddress::getAddress,
				CitizenAddress::getAddressArea,
				CitizenAddress::getAddressNumber,
				CitizenAddress::getAddressLetter,
				CitizenAddress::getApartmentNumber,
				CitizenAddress::getPostalCode,
				CitizenAddress::getCity,
				CitizenAddress::getCounty,
				CitizenAddress::getMunicipality,
				CitizenAddress::getCountry,
				CitizenAddress::getEmigrated,
				CitizenAddress::getAddressType,
				CitizenAddress::getXCoordLocal,
				CitizenAddress::getYCoordLocal)
			.containsExactly(tuple(
				status,
				nrDate.toString(),
				realEstateDescription,
				co,
				address,
				addressArea,
				addressNumber,
				addressLetter,
				apartmentNumber,
				postalCode,
				city,
				county,
				municipality,
				country,
				emigrated,
				addressType,
				xCoordLocal,
				yCoordLocal));
	}

	@Test
	void toCitizenAddresses_Null() {
		assertThat(CitizenAddressMapper.toCitizenAddresses(null)).isEmpty();
	}

	@Test
	void toCitizenAddresses_EmptyList() {
		assertThat(CitizenAddressMapper.toCitizenAddresses(List.of())).isEmpty();
	}

	@Test
	void toCitizenAddress_WithNullValues() {
		// Arrange
		final var addressEntity = CitizenAddressEntity.create();

		// Act
		final var result = CitizenAddressMapper.toCitizenAddresses(List.of(addressEntity));

		// Assert
		assertThat(result)
			.hasSize(1)
			.first()
			.satisfies(address -> {
				assertThat(address.getStatus()).isNull();
				assertThat(address.getNrDate()).isNull();
				assertThat(address.getRealEstateDescription()).isNull();
				assertThat(address.getCo()).isNull();
				assertThat(address.getAddress()).isNull();
				assertThat(address.getAddressArea()).isNull();
				assertThat(address.getAddressNumber()).isNull();
				assertThat(address.getAddressLetter()).isNull();
				assertThat(address.getApartmentNumber()).isNull();
				assertThat(address.getPostalCode()).isNull();
				assertThat(address.getCity()).isNull();
				assertThat(address.getCounty()).isNull();
				assertThat(address.getMunicipality()).isNull();
				assertThat(address.getCountry()).isNull();
				assertThat(address.getEmigrated()).isNull();
				assertThat(address.getAddressType()).isNull();
				assertThat(address.getXCoordLocal()).isNull();
				assertThat(address.getYCoordLocal()).isNull();
			});
	}
}
