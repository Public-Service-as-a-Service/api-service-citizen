package se.sundsvall.citizen.integration.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;

/**
 * Citizen repository tests.
 *
 * @see /src/test/resources/db/script/CitizenRepositoryTest.sql for data setup.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("junit")
@Sql(scripts = {
	"/db/script/truncate.sql",
	"/db/script/CitizenRepositoryTest.sql"
})
class CitizenRepositoryTest {

	private static final String CITIZEN_1_ID = "fb47e26c-1c27-11ee-be56-0242ac120002";
	private static final String CITIZEN_2_ID = "c31d362e-1c27-11ee-be56-0242ac120002";
	private static final String PERSONAL_NUMBER_1 = "19800101-1234";
	private static final String PERSONAL_NUMBER_2 = "19800102-5678";
	private static final OffsetDateTime BASE_TIME = OffsetDateTime.parse("2025-01-29T09:03:09Z");

	@Autowired
	private CitizenRepository citizenRepository;

	@Test
	void findById() {
		final var citizenOptional = citizenRepository.findById(CITIZEN_1_ID);

		assertThat(citizenOptional)
			.isPresent()
			.hasValueSatisfying(citizen -> {
				assertThat(citizen.getPersonId()).isEqualTo(CITIZEN_1_ID);
				assertThat(citizen.getPersonalNumber()).isEqualTo(PERSONAL_NUMBER_1);
				assertThat(citizen.getClassified()).isNull();
			});
	}

	@Test
	void findById_NotFound() {
		final var result = citizenRepository.findById("non-existent-id");

		assertThat(result).isNotPresent();
	}

	@Test
	void findByPersonalNumber() {
		final var citizenOptional = citizenRepository.findByPersonalNumber(PERSONAL_NUMBER_1);

		assertThat(citizenOptional)
			.isPresent()
			.hasValueSatisfying(citizen -> {
				assertThat(citizen.getPersonId()).isEqualTo(CITIZEN_1_ID);
				assertThat(citizen.getPersonalNumber()).isEqualTo(PERSONAL_NUMBER_1);
			});
	}

	@Test
	void findByPersonalNumber_NotFound() {
		final var result = citizenRepository.findByPersonalNumber("non-existent-number");

		assertThat(result).isNotPresent();
	}

	@Test
	void findAllByParameters_ShowClassified() {
		final var pageable = PageRequest.of(0, 10, Sort.by("personId"));
		final var result = citizenRepository.findAllByParameters(CITIZEN_1_ID, pageable, true);

		assertThat(result.getContent())
			.hasSize(1)
			.extracting(CitizenEntity::getPersonId)
			.containsExactly(CITIZEN_1_ID);
	}

	@Test
	void findAllByParameters_HideClassified() {
		final var pageable = PageRequest.of(0, 10, Sort.by("personId"));
		final var result = citizenRepository.findAllByParameters(CITIZEN_2_ID, pageable, false);

		assertThat(result.getContent()).isEmpty();
	}

	@Test
	void save() {
		final var citizenEntity = CitizenEntity.create()
			.withPersonalNumber("20000101-1234")
			.withGivenname("Test")
			.withLastname("Testsson")
			.withUpdatedAt(BASE_TIME);

		final var savedEntity = citizenRepository.save(citizenEntity);

		assertThat(savedEntity.getPersonId()).isNotNull();
		assertThat(savedEntity.getPersonalNumber()).isEqualTo("20000101-1234");
		assertThat(savedEntity.getGivenname()).isEqualTo("Test");
		assertThat(savedEntity.getLastname()).isEqualTo("Testsson");

		// Verify it can be retrieved
		final var retrievedEntity = citizenRepository.findById(savedEntity.getPersonId());
		assertThat(retrievedEntity).isPresent();
	}
}
