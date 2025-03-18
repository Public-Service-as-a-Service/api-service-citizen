package se.sundsvall.citizen.integration.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import se.sundsvall.citizen.integration.db.model.CitizenAddressEntity;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;
import se.sundsvall.citizen.integration.db.specification.CitizenAddressSpecification;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Citizen address repository tests.
 *
 * @see src/test/resources/db/script/CitizenAddressRepositoryTest.sql for data setup.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("junit")
@Sql(scripts = {
        "/db/script/truncate.sql",
        "/db/script/CitizenAddressRepositoryTest.sql"
})
class CitizenAddressRepositoryTest {

    private static final String ENTITY_1_ID = "c305f904-c9bf-4144-8656-a797acc90b74";
    private static final String CITIZEN_1_ID = "fb47e26c-1c27-11ee-be56-0242ac120002";
    private static final OffsetDateTime BASE_TIME = OffsetDateTime.parse("2025-03-10T17:28:54Z");

    @Autowired
    private CitizenAddressRepository citizenAddressRepository;

    @Test
    void findById() {
        final var addressOptional = citizenAddressRepository.findById(ENTITY_1_ID);

        assertThat(addressOptional)
                .isPresent()
                .hasValueSatisfying(entity -> {
                    assertThat(entity.getId()).isEqualTo(ENTITY_1_ID);
                    assertThat(entity.getCitizen().getPersonId()).isEqualTo(CITIZEN_1_ID);
                    assertThat(entity.getAddress()).isEqualTo("Test Street 1");
                    assertThat(entity.getCity()).isEqualTo("Test City 1");
                    assertThat(entity.getPostalCode()).isEqualTo("12345");
                    assertThat(entity.getStatus()).isEqualTo("ACTIVE");
                });
    }

    @Test
    void findById_NotFound() {
        final var result = citizenAddressRepository.findById("non-existent-id");

        assertThat(result).isNotPresent();
    }

    @Test
    void findWithChangedAddressSince() {
        final var fromDate = BASE_TIME.minusDays(1);
        final var addresses = citizenAddressRepository.findAll(
                CitizenAddressSpecification.hasChangedAddressSince(fromDate));

        assertThat(addresses)
                .isNotNull()
                .hasSize(1)
                .extracting(CitizenAddressEntity::getId)
                .containsExactly(ENTITY_1_ID);
    }

    @Test
    void findWithChangedAddressSince_NoResults() {
        final var fromDate = BASE_TIME.plusDays(1);
        final var addresses = citizenAddressRepository.findAll(
                CitizenAddressSpecification.hasChangedAddressSince(fromDate));

        assertThat(addresses)
                .isNotNull()
                .isEmpty();
    }

    @Test
    void findWithStatus() {
        final var addresses = citizenAddressRepository.findAll(
                CitizenAddressSpecification.withStatus("ACTIVE"));

        assertThat(addresses)
                .isNotNull()
                .hasSize(1)
                .extracting(CitizenAddressEntity::getStatus)
                .containsOnly("ACTIVE");
    }

    @Test
    void findWithCity() {
        final var addresses = citizenAddressRepository.findAll(
                CitizenAddressSpecification.withCity("Test City 1"));

        assertThat(addresses)
                .isNotNull()
                .hasSize(1)
                .extracting(CitizenAddressEntity::getCity)
                .containsOnly("Test City 1");
    }

    @Test
    void save() {
        final var citizen = CitizenEntity.create()
                .withPersonId(UUID.randomUUID().toString())
                .withPersonalNumber("20000101-1234")
                .withGivenname("Test")
                .withLastname("Testsson")
                .withCreatedAt(BASE_TIME)
                .withUpdatedAt(BASE_TIME);

        final var addressEntity = CitizenAddressEntity.create()
                .withCitizen(citizen)
                .withStatus("ACTIVE")
                .withAddress("Test Street 1")
                .withCity("Test City")
                .withPostalCode("12345")
                .withCreatedAt(BASE_TIME)
                .withUpdatedAt(BASE_TIME);

        final var savedEntity = citizenAddressRepository.save(addressEntity);

        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getAddress()).isEqualTo("Test Street 1");
        assertThat(savedEntity.getCity()).isEqualTo("Test City");
        assertThat(savedEntity.getPostalCode()).isEqualTo("12345");
        assertThat(savedEntity.getStatus()).isEqualTo("ACTIVE");
        assertThat(savedEntity.getCitizen().getPersonalNumber()).isEqualTo("20000101-1234");
        // assertThat(savedEntity.getCreatedAt()).isEqualTo(BASE_TIME);
        // assertThat(savedEntity.getUpdatedAt()).isEqualTo(BASE_TIME);

        // Verify it can be retrieved
        final var retrievedEntity = citizenAddressRepository.findById(savedEntity.getId());
        assertThat(retrievedEntity).isPresent();
    }

    @Test
    void testNoDirtOnCreatedBean() {
        assertThat(CitizenAddressEntity.create()).hasAllNullFieldsOrProperties();
        assertThat(new CitizenAddressEntity()).hasAllNullFieldsOrProperties();
    }
}