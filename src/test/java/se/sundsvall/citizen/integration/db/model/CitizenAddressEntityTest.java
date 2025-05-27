package se.sundsvall.citizen.integration.db.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CitizenAddressEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(CitizenAddressEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode()));
	}

	@Test
	void testBuilderMethods() {
		final var id = UUID.randomUUID().toString();
		final var citizen = new CitizenEntity();
		final var status = "status";
		final var nrDate = OffsetDateTime.now();
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
		final var createdAt = OffsetDateTime.now();
		final var updatedAt = OffsetDateTime.now().plusDays(1);

		final var entity = CitizenAddressEntity.create()
			.withId(id)
			.withCitizen(citizen)
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
			.withYCoordLocal(yCoordLocal)
			.withCreatedAt(createdAt)
			.withUpdatedAt(updatedAt);

		assertThat(entity).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(entity.getId()).isEqualTo(id);
		assertThat(entity.getCitizen()).isEqualTo(citizen);
		assertThat(entity.getStatus()).isEqualTo(status);
		assertThat(entity.getNrDate()).isEqualTo(nrDate);
		assertThat(entity.getRealEstateDescription()).isEqualTo(realEstateDescription);
		assertThat(entity.getCo()).isEqualTo(co);
		assertThat(entity.getAddress()).isEqualTo(address);
		assertThat(entity.getAddressArea()).isEqualTo(addressArea);
		assertThat(entity.getAddressNumber()).isEqualTo(addressNumber);
		assertThat(entity.getAddressLetter()).isEqualTo(addressLetter);
		assertThat(entity.getApartmentNumber()).isEqualTo(apartmentNumber);
		assertThat(entity.getPostalCode()).isEqualTo(postalCode);
		assertThat(entity.getCity()).isEqualTo(city);
		assertThat(entity.getCounty()).isEqualTo(county);
		assertThat(entity.getMunicipality()).isEqualTo(municipality);
		assertThat(entity.getCountry()).isEqualTo(country);
		assertThat(entity.getEmigrated()).isEqualTo(emigrated);
		assertThat(entity.getAddressType()).isEqualTo(addressType);
		assertThat(entity.getXCoordLocal()).isEqualTo(xCoordLocal);
		assertThat(entity.getYCoordLocal()).isEqualTo(yCoordLocal);
		assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
		assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(CitizenAddressEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new CitizenAddressEntity()).hasAllNullFieldsOrProperties();
	}
}
