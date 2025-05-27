package se.sundsvall.citizen.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class PersonGuidBatchTest {

	@Test
	void testBean() {
		assertThat(PersonGuidBatch.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var personNumber = "198001011234";
		final var personId = UUID.randomUUID();
		final var success = true;
		final var errorMessage = "Test error message";

		final var personGuidBatch = PersonGuidBatch.create()
			.withPersonNumber(personNumber)
			.withPersonId(personId)
			.withSuccess(success)
			.withErrorMessage(errorMessage);

		assertThat(personGuidBatch).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(personGuidBatch.getPersonNumber()).isEqualTo(personNumber);
		assertThat(personGuidBatch.getPersonId()).isEqualTo(personId);
		assertThat(personGuidBatch.isSuccess()).isTrue();
		assertThat(personGuidBatch.getErrorMessage()).isEqualTo(errorMessage);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		final var personGuidBatch = PersonGuidBatch.create();

		assertThat(personGuidBatch.getPersonNumber()).isNull();
		assertThat(personGuidBatch.getPersonId()).isNull();
		assertThat(personGuidBatch.isSuccess()).isFalse();
		assertThat(personGuidBatch.getErrorMessage()).isNull();
	}

	@Test
	void testSuccessScenario() {
		final var personGuidBatch = PersonGuidBatch.create()
			.withPersonNumber("198001011234")
			.withPersonId(UUID.randomUUID())
			.withSuccess(true);

		assertThat(personGuidBatch.isSuccess()).isTrue();
		assertThat(personGuidBatch.getErrorMessage()).isNull();
	}

	@Test
	void testFailureScenario() {
		final var errorMessage = "Person not found";
		final var personGuidBatch = PersonGuidBatch.create()
			.withPersonNumber("198001011234")
			.withSuccess(false)
			.withErrorMessage(errorMessage);

		assertThat(personGuidBatch.isSuccess()).isFalse();
		assertThat(personGuidBatch.getPersonId()).isNull();
		assertThat(personGuidBatch.getErrorMessage()).isEqualTo(errorMessage);
	}
}
