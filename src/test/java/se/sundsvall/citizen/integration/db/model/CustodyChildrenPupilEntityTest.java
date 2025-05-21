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
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CustodyChildrenPupilEntityTest {

	@BeforeAll
	static void setup() {
		registerValueGenerator(() -> now().plusDays(new Random().nextInt()), OffsetDateTime.class);
		registerValueGenerator(() -> {
			var citizen = new CitizenEntity();
			citizen.setPersonId(UUID.randomUUID().toString());
			return citizen;
		}, CitizenEntity.class);
	}

	@Test
	void testBean() {
		assertThat(CustodyChildrenPupilEntity.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals()));
	}

	@Test
	void testToString() {
		// Specific test for toString behavior
		var entity = CustodyChildrenPupilEntity.create()
			.withId("testId")
			.withCitizen(CitizenEntity.create().withPersonId("citizenId"))
			.withPersonnumber("12345")
			.withTypeOfSchool("school")
			.withUnRegCode("code")
			.withUnRegDate(OffsetDateTime.parse("2025-01-29T10:18:33Z"))
			.withCreatedAt(OffsetDateTime.parse("2025-01-29T10:18:33Z"))
			.withUpdatedAt(OffsetDateTime.parse("2025-01-29T10:18:33Z"));

		String expected = "CustodyChildrenPupilEntity [id=testId, citizen=citizenId, " +
			"personnumber=12345, typeOfSchool=school, unRegCode=code, " +
			"unRegDate=2025-01-29T10:18:33Z, createdAt=2025-01-29T10:18:33Z, " +
			"updatedAt=2025-01-29T10:18:33Z]";

		assertThat(entity.toString()).isEqualTo(expected);
	}

	@Test
	void testBuilderMethods() {
		final var id = UUID.randomUUID().toString();
		final var citizen = new CitizenEntity();
		final var personnumber = "personnumber";
		final var typeOfSchool = "typeOfSchool";
		final var unRegCode = "unRegCode";
		final var unRegDate = OffsetDateTime.now();
		final var createdAt = OffsetDateTime.now();
		final var updatedAt = OffsetDateTime.now().plusDays(1);

		final var entity = CustodyChildrenPupilEntity.create()
			.withId(id)
			.withCitizen(citizen)
			.withPersonnumber(personnumber)
			.withTypeOfSchool(typeOfSchool)
			.withUnRegCode(unRegCode)
			.withUnRegDate(unRegDate)
			.withCreatedAt(createdAt)
			.withUpdatedAt(updatedAt);

		assertThat(entity).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(entity.getId()).isEqualTo(id);
		assertThat(entity.getCitizen()).isEqualTo(citizen);
		assertThat(entity.getPersonnumber()).isEqualTo(personnumber);
		assertThat(entity.getTypeOfSchool()).isEqualTo(typeOfSchool);
		assertThat(entity.getUnRegCode()).isEqualTo(unRegCode);
		assertThat(entity.getUnRegDate()).isEqualTo(unRegDate);
		assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
		assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(CustodyChildrenPupilEntity.create()).hasAllNullFieldsOrProperties();
		assertThat(new CustodyChildrenPupilEntity()).hasAllNullFieldsOrProperties();
	}
}
