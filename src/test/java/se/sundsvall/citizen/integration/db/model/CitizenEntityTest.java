package se.sundsvall.citizen.integration.db.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.google.code.beanmatchers.BeanMatchers.registerValueGenerator;
import static java.time.OffsetDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CitizenEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
	}

	@Test
	void testBean() {
		assertThat(CitizenEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var personId = UUID.randomUUID().toString();
		final var personalNumber = "personalNumber";
		final var givenname = "givenname";
		final var lastname = "lastname";
		final var gender = "gender";
		final var civilStatus = "civilStatus";
		final var nrDate = OffsetDateTime.now();
		final var classified = "classified";
		final var protectedNr = "protectedNr";
		final var createdAt = OffsetDateTime.now();
		final var updatedAt = OffsetDateTime.now().plusDays(1);
		final var addresses = new ArrayList<CitizenAddressEntity>();
		final var custodyChildren = new ArrayList<CustodyChildrenPupilEntity>();

		final var entity = CitizenEntity.create()
			.withPersonId(personId)
			.withPersonalNumber(personalNumber)
			.withGivenname(givenname)
			.withLastname(lastname)
			.withGender(gender)
			.withCivilStatus(civilStatus)
			.withNrDate(nrDate)
			.withClassified(classified)
			.withProtectedNr(protectedNr)
			.withCreatedAt(createdAt)
			.withUpdatedAt(updatedAt)
			.withAddresses(addresses)
			.withCustodyChildren(custodyChildren);

		assertThat(entity).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(entity.getPersonId()).isEqualTo(personId);
		assertThat(entity.getPersonalNumber()).isEqualTo(personalNumber);
		assertThat(entity.getGivenname()).isEqualTo(givenname);
		assertThat(entity.getLastname()).isEqualTo(lastname);
		assertThat(entity.getGender()).isEqualTo(gender);
		assertThat(entity.getCivilStatus()).isEqualTo(civilStatus);
		assertThat(entity.getNrDate()).isEqualTo(nrDate);
		assertThat(entity.getClassified()).isEqualTo(classified);
		assertThat(entity.getProtectedNr()).isEqualTo(protectedNr);
		assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
		assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
		assertThat(entity.getAddresses()).isEqualTo(addresses);
		assertThat(entity.getCustodyChildren()).isEqualTo(custodyChildren);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(CitizenEntity.create())
			.hasAllNullFieldsOrPropertiesExcept("addresses", "custodyChildren");
		assertThat(new CitizenEntity())
			.hasAllNullFieldsOrPropertiesExcept("addresses", "custodyChildren");
	}

}
