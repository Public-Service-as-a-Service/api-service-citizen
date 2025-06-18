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
import java.util.UUID;
import org.junit.jupiter.api.Test;

class FindCitizenBatchRequestTest {

	@Test
	void testBean() {
		assertThat(FindCitizenBatchRequest.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var personIds = List.of(UUID.randomUUID(), UUID.randomUUID());
		final var showClassified = true;
		final var page = 2;
		final var limit = 25;

		final var findCitizenBatchRequest = FindCitizenBatchRequest.create()
			.withPersonIds(personIds)
			.withShowClassified(showClassified)
			.withPage(page)
			.withLimit(limit);

		assertThat(findCitizenBatchRequest).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(findCitizenBatchRequest.getPersonIds()).isEqualTo(personIds);
		assertThat(findCitizenBatchRequest.isShowClassified()).isEqualTo(showClassified);
		assertThat(findCitizenBatchRequest.getPage()).isEqualTo(page);
		assertThat(findCitizenBatchRequest.getLimit()).isEqualTo(limit);
	}

	@Test
	void testNoDirtOnCreatedBeanExceptDefaultValues() {
		final var request = FindCitizenBatchRequest.create();

		assertThat(request.getPersonIds()).isNull();
		assertThat(request.isShowClassified()).isFalse();
		assertThat(request.getPage()).isEqualTo(1);
		assertThat(request.getLimit()).isEqualTo(100);
	}

	@Test
	void testDefaultValues() {
		final var request = new FindCitizenBatchRequest();

		assertThat(request.isShowClassified()).isFalse();
		assertThat(request.getPage()).isEqualTo(1);
		assertThat(request.getLimit()).isEqualTo(100);
	}
}
