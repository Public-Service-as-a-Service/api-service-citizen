package se.sundsvall.citizen.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

import java.util.List;

import org.junit.jupiter.api.Test;

class FindPersonGuidBatchRequestTest {

	@Test
	void testBean() {
		assertThat(FindPersonGuidBatchRequest.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var personalNumbers = List.of("198001011234", "198001011235");
		final var page = 2;
		final var limit = 25;

		final var findPersonGuidBatchRequest = FindPersonGuidBatchRequest.create()
			.withPersonalNumbers(personalNumbers)
			.withPage(page)
			.withLimit(limit);

		assertThat(findPersonGuidBatchRequest).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(findPersonGuidBatchRequest.getPersonalNumbers())
			.isEqualTo(personalNumbers)
			.hasSize(2)
			.containsExactly("198001011234", "198001011235");
		assertThat(findPersonGuidBatchRequest.getPage()).isEqualTo(page);
		assertThat(findPersonGuidBatchRequest.getLimit()).isEqualTo(limit);
	}

	@Test
	void testNoDirtOnCreatedBeanExceptDefaultValues() {
		final var request = FindPersonGuidBatchRequest.create();

		assertThat(request.getPersonalNumbers()).isNull();
		assertThat(request.getPage()).isEqualTo(1);
		assertThat(request.getLimit()).isEqualTo(100);
	}

	@Test
	void testDefaultValues() {
		final var request = new FindPersonGuidBatchRequest();

		assertThat(request.getPage()).isEqualTo(1);
		assertThat(request.getLimit()).isEqualTo(100);
	}
}
