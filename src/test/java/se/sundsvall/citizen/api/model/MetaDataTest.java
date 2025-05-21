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

class MetaDataTest {

	@Test
	void testBean() {
		assertThat(MetaData.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var page = 5;
		final var limit = 20;
		final var count = 13;
		final var totalRecords = 98L;
		final var totalPages = 23;

		final var metaData = MetaData.create()
			.withPage(page)
			.withLimit(limit)
			.withCount(count)
			.withTotalRecords(totalRecords)
			.withTotalPages(totalPages);

		assertThat(metaData).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(metaData.getPage()).isEqualTo(page);
		assertThat(metaData.getLimit()).isEqualTo(limit);
		assertThat(metaData.getCount()).isEqualTo(count);
		assertThat(metaData.getTotalRecords()).isEqualTo(totalRecords);
		assertThat(metaData.getTotalPages()).isEqualTo(totalPages);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		final var metaData = MetaData.create();

		assertThat(metaData.getPage()).isZero();
		assertThat(metaData.getLimit()).isZero();
		assertThat(metaData.getCount()).isZero();
		assertThat(metaData.getTotalRecords()).isZero();
		assertThat(metaData.getTotalPages()).isZero();
	}
}
