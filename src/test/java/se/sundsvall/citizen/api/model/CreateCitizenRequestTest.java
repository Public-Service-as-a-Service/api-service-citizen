package se.sundsvall.citizen.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

import org.junit.jupiter.api.Test;

class CreateCitizenRequestTest {

	@Test
	void testBean() {
		assertThat(CreateCitizenRequest.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var personalNumber = "198001011234";

		final var createCitizenRequest = CreateCitizenRequest.create()
			.withPersonalNumber(personalNumber);

		assertThat(createCitizenRequest).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(createCitizenRequest.getPersonalNumber()).isEqualTo(personalNumber);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(CreateCitizenRequest.create()).hasAllNullFieldsOrProperties();
		assertThat(new CreateCitizenRequest()).hasAllNullFieldsOrProperties();
	}
}
