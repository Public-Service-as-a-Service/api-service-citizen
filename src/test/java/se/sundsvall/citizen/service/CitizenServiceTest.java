package se.sundsvall.citizen.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.zalando.problem.Status.BAD_REQUEST;
import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.zalando.problem.ThrowableProblem;
import se.sundsvall.citizen.api.model.CitizenExtended;
import se.sundsvall.citizen.api.model.CitizenWithChangedAddress;
import se.sundsvall.citizen.api.model.ModelPostPerson;
import se.sundsvall.citizen.integration.db.CitizenAddressRepository;
import se.sundsvall.citizen.integration.db.CitizenRepository;
import se.sundsvall.citizen.integration.db.model.CitizenAddressEntity;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;
import se.sundsvall.citizen.integration.db.specification.CitizenAddressSpecification;
import se.sundsvall.citizen.integration.party.PartyIntegration;
import se.sundsvall.citizen.service.mapper.CitizenAddressMapper;
import se.sundsvall.citizen.service.mapper.CitizenMapper;

@ExtendWith(MockitoExtension.class)
class CitizenServiceTest {

	@Mock
	private CitizenRepository citizenRepositoryMock;

	@Mock
	private CitizenAddressRepository citizenAddressRepositoryMock;

	@Mock
	private ObjectMapper objectMapperMock;

	@InjectMocks
	private CitizenService citizenService;

	@Mock
	private PartyIntegration partyIntegrationMock;

	@Test
	void getCitizenById() {
		// Arrange
		final var personId = UUID.randomUUID();
		final var citizenEntity = CitizenEntity.create().withPersonId(personId.toString());
		final var expectedCitizen = new CitizenExtended();

		when(citizenRepositoryMock.findById(personId.toString())).thenReturn(Optional.of(citizenEntity));

		try (MockedStatic<CitizenMapper> mapperMock = Mockito.mockStatic(CitizenMapper.class)) {
			mapperMock.when(() -> CitizenMapper.toCitizenExtended(any())).thenReturn(expectedCitizen);

			// Act
			final var result = citizenService.getCitizenById(personId, true);

			// Assert
			assertThat(result).isSameAs(expectedCitizen);
			verify(citizenRepositoryMock).findById(personId.toString());
			mapperMock.verify(() -> CitizenMapper.toCitizenExtended(same(citizenEntity)));
		}
	}

	@Test
	void getCitizenById_NotFound() {
		// Arrange
		final var personId = UUID.randomUUID();
		when(citizenRepositoryMock.findById(personId.toString())).thenReturn(Optional.empty());

		// Act & Assert
		final var exception = assertThrows(ThrowableProblem.class,
			() -> citizenService.getCitizenById(personId, true));

		assertThat(exception.getStatus()).isEqualTo(NOT_FOUND);
		assertThat(exception.getMessage()).contains(String.format("No citizen found with ID: %s", personId));
	}

	@Test
	void getCitizenById_ClassifiedHidden() {
		// Arrange
		final var personId = UUID.randomUUID();
		final var citizenEntity = CitizenEntity.create()
			.withPersonId(personId.toString())
			.withClassified("CLASSIFIED");

		when(citizenRepositoryMock.findById(personId.toString())).thenReturn(Optional.of(citizenEntity));

		// Act
		final var result = citizenService.getCitizenById(personId, false);

		// Assert
		assertThat(result).isNull();
		verify(citizenRepositoryMock).findById(personId.toString());
	}

	@Test
	void getCitizensByIds() {
		// Arrange
		final var personId1 = UUID.randomUUID();
		final var personId2 = UUID.randomUUID();
		final var citizenEntity1 = CitizenEntity.create().withPersonId(personId1.toString());
		final var citizenEntity2 = CitizenEntity.create().withPersonId(personId2.toString());
		final var expectedCitizen1 = new CitizenExtended();
		final var expectedCitizen2 = new CitizenExtended();

		when(citizenRepositoryMock.findById(personId1.toString())).thenReturn(Optional.of(citizenEntity1));
		when(citizenRepositoryMock.findById(personId2.toString())).thenReturn(Optional.of(citizenEntity2));

		try (MockedStatic<CitizenMapper> mapperMock = Mockito.mockStatic(CitizenMapper.class)) {
			mapperMock.when(() -> CitizenMapper.toCitizenExtended(citizenEntity1)).thenReturn(expectedCitizen1);
			mapperMock.when(() -> CitizenMapper.toCitizenExtended(citizenEntity2)).thenReturn(expectedCitizen2);

			// Act
			final var result = citizenService.getCitizensByIds(List.of(personId1, personId2), true);

			// Assert
			assertThat(result)
				.hasSize(2)
				.containsExactly(expectedCitizen1, expectedCitizen2);
		}
	}

	@Test
	void getCitizensWithChangedAddress() {
		// Arrange
		final var changedDateFrom = OffsetDateTime.now();
		final var citizenAddressEntity = new CitizenAddressEntity();
		final var expectedChangedAddress = new CitizenWithChangedAddress();

		try (MockedStatic<CitizenAddressSpecification> specMock = Mockito.mockStatic(CitizenAddressSpecification.class);
			MockedStatic<CitizenAddressMapper> mapperMock = Mockito.mockStatic(CitizenAddressMapper.class)) {

			Specification<CitizenAddressEntity> mockSpec = (root, query, cb) -> cb.conjunction();
			specMock.when(() -> CitizenAddressSpecification.hasChangedAddressSince(changedDateFrom))
				.thenReturn(mockSpec);

			when(citizenAddressRepositoryMock.findAll(any(Specification.class)))
				.thenReturn(List.of(citizenAddressEntity));

			mapperMock.when(() -> CitizenAddressMapper.toCitizenWithChangedAddress(any()))
				.thenReturn(expectedChangedAddress);

			// Act
			final var result = citizenService.getCitizensWithChangedAddress(changedDateFrom);

			// Assert
			assertThat(result)
				.isNotNull()
				.hasSize(1)
				.containsExactly(expectedChangedAddress);
		}
	}

	@Test
	void getCitizensWithChangedAddress_NoResults() {
		// Arrange
		final var changedDateFrom = OffsetDateTime.now();

		try (MockedStatic<CitizenAddressSpecification> specMock = Mockito.mockStatic(CitizenAddressSpecification.class)) {
			Specification<CitizenAddressEntity> mockSpec = (root, query, cb) -> cb.conjunction();
			specMock.when(() -> CitizenAddressSpecification.hasChangedAddressSince(changedDateFrom))
				.thenReturn(mockSpec);

			when(citizenAddressRepositoryMock.findAll(any(Specification.class)))
				.thenReturn(Collections.emptyList());

			// Act
			final var result = citizenService.getCitizensWithChangedAddress(changedDateFrom);

			// Assert
			assertThat(result).isEmpty();
		}
	}

	@Test
	void getPersonalNumberById() {
		// Arrange
		final var personId = UUID.randomUUID();
		final var citizenEntity = CitizenEntity.create()
			.withPersonId(personId.toString())
			.withPersonalNumber("198001011234");

		when(citizenRepositoryMock.findById(personId.toString())).thenReturn(Optional.of(citizenEntity));

		// Act
		final var result = citizenService.getPersonalNumberById(personId);

		// Assert
		assertThat(result).isEqualTo("198001011234");
		verify(citizenRepositoryMock).findById(personId.toString());
	}

	@Test
	void getPersonalNumberById_NotFound() {
		// Arrange
		final var personId = UUID.randomUUID();
		when(citizenRepositoryMock.findById(personId.toString())).thenReturn(Optional.empty());

		// Act & Assert
		final var exception = assertThrows(ThrowableProblem.class,
			() -> citizenService.getPersonalNumberById(personId));

		assertThat(exception.getStatus()).isEqualTo(NOT_FOUND);
		assertThat(exception.getMessage()).contains(String.format("No citizen found with ID: %s", personId));
	}

	@Test
	void getPersonIdByPersonalNumber() {
		// Arrange
		final var personalNumber = "198001011234";
		final var personId = UUID.randomUUID().toString();
		final var municipalityId = "1440";
		final var citizenEntity = CitizenEntity.create().withPersonId(personId);

		when(citizenRepositoryMock.findByPersonalNumber(personalNumber)).thenReturn(Optional.of(citizenEntity));

		// Act
		final var result = citizenService.getPersonIdByPersonalNumber(personalNumber, municipalityId);

		// Assert
		assertThat(result).isEqualTo(personId);
		verify(citizenRepositoryMock).findByPersonalNumber(personalNumber);
	}

	@Test
	void getPersonIdByPersonalNumberInSundsvall() {
		// Arrange
		final var personalNumber = "198001011234";
		final var personId = UUID.randomUUID().toString();
		final var municipalityId = "2281";
		final var type = "PRIVATE";

		when(partyIntegrationMock.getPartyId(personalNumber, municipalityId, type)).thenReturn(personId);
		// Act
		final var result = citizenService.getPersonIdByPersonalNumber(personalNumber, municipalityId);

		// Assert
		assertThat(result).isEqualTo(personId);
		verify(partyIntegrationMock).getPartyId(personalNumber, municipalityId, type);
	}

	@Test
	void getPersonIdByPersonalNumber_NotFound() {
		// Arrange
		final var personalNumber = "198001011234";
		final var municipalityId = "2181";
		when(citizenRepositoryMock.findByPersonalNumber(personalNumber)).thenReturn(Optional.empty());

		// Act & Assert
		final var exception = assertThrows(ThrowableProblem.class,
			() -> citizenService.getPersonIdByPersonalNumber(personalNumber, municipalityId));

		assertThat(exception.getStatus()).isEqualTo(NOT_FOUND);
		assertThat(exception.getMessage())
			.contains(String.format("No citizen found with personal number: %s", personalNumber));
	}

	@Test
	void getPersonIdsInBatch() {
		// Arrange
		final var personalNumber = "198001011234";
		final var personId = UUID.randomUUID();
		final var citizenEntity = CitizenEntity.create().withPersonId(personId.toString());

		when(citizenRepositoryMock.findByPersonalNumber(personalNumber)).thenReturn(Optional.of(citizenEntity));

		// Act
		final var result = citizenService.getPersonIdsInBatch(List.of(personalNumber));

		// Assert
		assertThat(result)
			.hasSize(1)
			.first()
			.satisfies(batch -> {
				assertThat(batch.getPersonNumber()).isEqualTo(personalNumber);
				assertThat(batch.getPersonId()).isEqualTo(personId);
				assertThat(batch.isSuccess()).isTrue();
				assertThat(batch.getErrorMessage()).isNull();
			});
	}

	@Test
	void getPersonIdsInBatch_NotFound() {
		// Arrange
		final var personalNumber = "198001011234";
		when(citizenRepositoryMock.findByPersonalNumber(personalNumber)).thenReturn(Optional.empty());

		// Act
		final var result = citizenService.getPersonIdsInBatch(List.of(personalNumber));

		// Assert
		assertThat(result)
			.hasSize(1)
			.first()
			.satisfies(batch -> {
				assertThat(batch.getPersonNumber()).isEqualTo(personalNumber);
				assertThat(batch.getPersonId()).isNull();
				assertThat(batch.isSuccess()).isFalse();
				assertThat(batch.getErrorMessage()).isEqualTo("Citizen not found");
			});
	}

	@Test
	void createPerson() {
		// Arrange
		final var personId = UUID.randomUUID();
		final var modelPostPerson = new ModelPostPerson("198001011234");
		final var citizenEntity = CitizenEntity.create()
			.withPersonId(personId.toString())
			.withPersonalNumber("198001011234");

		when(citizenRepositoryMock.findByPersonalNumber(modelPostPerson.getPersonalNumber()))
			.thenReturn(Optional.empty());
		when(citizenRepositoryMock.save(any())).thenReturn(citizenEntity);

		// Act
		final var result = citizenService.createPerson(modelPostPerson);

		// Assert
		assertThat(result).isEqualTo(personId);
		verify(citizenRepositoryMock).save(any());
	}

	@Test
	void createPerson_AlreadyExists() {
		// Arrange
		final var modelPostPerson = new ModelPostPerson("198001011234");
		when(citizenRepositoryMock.findByPersonalNumber(modelPostPerson.getPersonalNumber()))
			.thenReturn(Optional.of(new CitizenEntity()));

		// Act & Assert
		final var exception = assertThrows(ThrowableProblem.class,
			() -> citizenService.createPerson(modelPostPerson));

		assertThat(exception.getStatus()).isEqualTo(CONFLICT);
		assertThat(exception.getMessage())
			.contains("Person with personal number 198001011234 already exists");
		verifyNoInteractions(objectMapperMock);
	}

	@Test
	void createPerson_InvalidInput() {
		// Act & Assert
		final var exception = assertThrows(ThrowableProblem.class,
			() -> citizenService.createPerson(null));

		assertThat(exception.getStatus()).isEqualTo(BAD_REQUEST);
		assertThat(exception.getMessage()).contains("Personal number is required");
		verifyNoInteractions(citizenRepositoryMock, objectMapperMock);
	}
}
