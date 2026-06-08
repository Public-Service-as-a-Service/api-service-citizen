package se.sundsvall.citizen.service.mapper;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.citizen.api.model.CitizenAddress;
import se.sundsvall.citizen.api.model.CitizenExtended;
import se.sundsvall.citizen.integration.db.model.CitizenAddressEntity;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for CitizenMapper.
 *
 * @author  TennyOne
 * @created 2025-01-29 11:20:53
 */
@ExtendWith(MockitoExtension.class)
class CitizenMapperTest {

	private static final OffsetDateTime CURRENT_TIME = OffsetDateTime.parse("2025-01-29T11:20:53Z");

	@Test
	void toCitizenExtended() {
		// Arrange
		final var personId = UUID.randomUUID().toString();
		final var givenname = "givenname";
		final var lastname = "lastname";
		final var gender = "gender";
		final var civilStatus = "civilStatus";
		final var nrDate = CURRENT_TIME;
		final var classified = "classified";
		final var protectedNr = "protectedNr";
		final var addresses = List.of(new CitizenAddressEntity());
		final var mappedAddresses = List.of(new CitizenAddress());

		final var citizenEntity = CitizenEntity.create()
			.withPersonId(personId)
			.withGivenname(givenname)
			.withLastname(lastname)
			.withGender(gender)
			.withCivilStatus(civilStatus)
			.withNrDate(nrDate)
			.withClassified(classified)
			.withProtectedNr(protectedNr)
			.withAddresses(addresses);

		try (MockedStatic<CitizenAddressMapper> addressMapperMock = Mockito.mockStatic(CitizenAddressMapper.class)) {
			addressMapperMock.when(() -> CitizenAddressMapper.toCitizenAddresses(addresses))
				.thenReturn(mappedAddresses);

			// Act
			final var result = CitizenMapper.toCitizenExtended(citizenEntity);

			// Assert
			assertThat(result).isNotNull();
			assertThat(result.getPersonId()).isEqualTo(UUID.fromString(personId));
			assertThat(result.getGivenname()).isEqualTo(givenname);
			assertThat(result.getLastname()).isEqualTo(lastname);
			assertThat(result.getGender()).isEqualTo(gender);
			assertThat(result.getCivilStatus()).isEqualTo(civilStatus);
			assertThat(result.getNrDate()).isEqualTo(nrDate.toString());
			assertThat(result.getClassified()).isEqualTo(classified);
			assertThat(result.getProtectedNr()).isEqualTo(protectedNr);
			assertThat(result.getAddresses()).isEqualTo(mappedAddresses);

			addressMapperMock.verify(() -> CitizenAddressMapper.toCitizenAddresses(addresses));
		}
	}

	@Test
	void toCitizenExtended_Null() {
		assertThat(CitizenMapper.toCitizenExtended(null)).isNull();
	}

	@Test
	void toCitizenExtended_WithNullValues() {
		// Arrange
		final var personId = UUID.randomUUID().toString();
		final var citizenEntity = CitizenEntity.create()
			.withPersonId(personId);

		try (MockedStatic<CitizenAddressMapper> addressMapperMock = Mockito.mockStatic(CitizenAddressMapper.class)) {
			addressMapperMock.when(() -> CitizenAddressMapper.toCitizenAddresses(Collections.emptyList()))
				.thenReturn(Collections.emptyList());

			// Act
			final var result = CitizenMapper.toCitizenExtended(citizenEntity);

			// Assert
			assertThat(result).isNotNull();
			assertThat(result.getPersonId()).isEqualTo(UUID.fromString(personId));
			assertThat(result.getGivenname()).isNull();
			assertThat(result.getLastname()).isNull();
			assertThat(result.getGender()).isNull();
			assertThat(result.getCivilStatus()).isNull();
			assertThat(result.getNrDate()).isNull();
			assertThat(result.getClassified()).isNull();
			assertThat(result.getProtectedNr()).isNull();
			assertThat(result.getAddresses()).isEmpty();

			addressMapperMock.verify(() -> CitizenAddressMapper.toCitizenAddresses(Collections.emptyList()));
		}
	}

	@Test
	void toCitizenExtended_WithEmptyAddresses() {
		// Arrange
		final var personId = UUID.randomUUID().toString();
		final var citizenEntity = CitizenEntity.create()
			.withPersonId(personId)
			.withAddresses(Collections.emptyList());

		try (MockedStatic<CitizenAddressMapper> addressMapperMock = Mockito.mockStatic(CitizenAddressMapper.class)) {
			addressMapperMock.when(() -> CitizenAddressMapper.toCitizenAddresses(Collections.emptyList()))
				.thenReturn(Collections.emptyList());

			// Act
			final var result = CitizenMapper.toCitizenExtended(citizenEntity);

			// Assert
			assertThat(result).isNotNull();
			assertThat(result.getPersonId()).isEqualTo(UUID.fromString(personId));
			assertThat(result.getAddresses()).isEmpty();

			addressMapperMock.verify(() -> CitizenAddressMapper.toCitizenAddresses(Collections.emptyList()));
		}
	}

	@Test
	void toCitizenEntity() {
		// Arrange
		final var personalNumber = "199001011234";
		final var givenname = "Auto";
		final var lastname = "Godkand";
		final var gender = "MALE";
		final var civilStatus = "SINGLE";
		final var classified = "classified";
		final var protectedNr = "protectedNr";
		final var inputAddress = new CitizenAddress();
		final var mappedAddressEntity = CitizenAddressEntity.create();

		final var citizen = CitizenExtended.create()
			.withPersonalNumber(personalNumber)
			.withGivenname(givenname)
			.withLastname(lastname)
			.withGender(gender)
			.withCivilStatus(civilStatus)
			.withClassified(classified)
			.withProtectedNr(protectedNr)
			.withAddresses(List.of(inputAddress));

		try (MockedStatic<CitizenAddressMapper> addressMapperMock = Mockito.mockStatic(CitizenAddressMapper.class)) {
			addressMapperMock.when(() -> CitizenAddressMapper.toCitizenAddressEntity(inputAddress))
				.thenReturn(mappedAddressEntity);

			// Act
			final var result = CitizenMapper.toCitizenEntity(citizen);

			// Assert
			assertThat(result).isNotNull();
			assertThat(result.getPersonId()).isNull();
			assertThat(result.getPersonalNumber()).isEqualTo(personalNumber);
			assertThat(result.getGivenname()).isEqualTo(givenname);
			assertThat(result.getLastname()).isEqualTo(lastname);
			assertThat(result.getGender()).isEqualTo(gender);
			assertThat(result.getCivilStatus()).isEqualTo(civilStatus);
			assertThat(result.getClassified()).isEqualTo(classified);
			assertThat(result.getProtectedNr()).isEqualTo(protectedNr);
			assertThat(result.getAddresses()).hasSize(1);
			// reference checks (entity equals() is bidirectional, so avoid equals-based assertions)
			assertThat(result.getAddresses().get(0)).isSameAs(mappedAddressEntity);
			assertThat(mappedAddressEntity.getCitizen()).isSameAs(result);

			addressMapperMock.verify(() -> CitizenAddressMapper.toCitizenAddressEntity(inputAddress));
		}
	}

	@Test
	void toCitizenEntity_Null() {
		assertThat(CitizenMapper.toCitizenEntity(null)).isNull();
	}

	@Test
	void toCitizenEntity_NullAddresses() {
		// Arrange
		final var citizen = CitizenExtended.create().withPersonalNumber("199001011234");

		// Act
		final var result = CitizenMapper.toCitizenEntity(citizen);

		// Assert
		assertThat(result).isNotNull();
		assertThat(result.getPersonalNumber()).isEqualTo("199001011234");
		assertThat(result.getAddresses()).isEmpty();
	}
}
