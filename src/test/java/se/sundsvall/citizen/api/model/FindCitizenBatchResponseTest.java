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

class FindCitizenBatchResponseTest {

	@Test
	void testBean() {
		assertThat(FindCitizenBatchResponse.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var metaData = MetaData.create()
			.withPage(1)
			.withLimit(10)
			.withCount(2)
			.withTotalPages(1)
			.withTotalRecords(2);

		final var citizen1 = CitizenExtended.create()
			.withPersonId(UUID.randomUUID());
		final var citizen2 = CitizenExtended.create()
			.withPersonId(UUID.randomUUID());
		final var citizens = List.of(citizen1, citizen2);

		final var findCitizenBatchResponse = FindCitizenBatchResponse.create()
			.withMetaData(metaData)
			.withCitizens(citizens);

		assertThat(findCitizenBatchResponse).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(findCitizenBatchResponse.getMetaData()).isEqualTo(metaData);
		assertThat(findCitizenBatchResponse.getCitizens())
			.isEqualTo(citizens)
			.hasSize(2)
			.containsExactly(citizen1, citizen2);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(FindCitizenBatchResponse.create()).hasAllNullFieldsOrProperties();
		assertThat(new FindCitizenBatchResponse()).hasAllNullFieldsOrProperties();
	}
}
