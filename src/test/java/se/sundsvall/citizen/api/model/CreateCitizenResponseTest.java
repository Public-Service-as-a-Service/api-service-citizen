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

class CreateCitizenResponseTest {

	@Test
	void testBean() {
		assertThat(CreateCitizenResponse.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters(),
			hasValidBeanHashCode(),
			hasValidBeanEquals(),
			hasValidBeanToString()));
	}

	@Test
	void testBuilderMethods() {
		final var personId = UUID.randomUUID();
		final var metaData = MetaData.create()
			.withPage(1)
			.withLimit(10)
			.withCount(1)
			.withTotalPages(1)
			.withTotalRecords(1);

		final var createCitizenResponse = CreateCitizenResponse.create()
			.withPersonId(personId)
			.withMetaData(metaData);

		assertThat(createCitizenResponse).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(createCitizenResponse.getPersonId()).isEqualTo(personId);
		assertThat(createCitizenResponse.getMetaData()).isEqualTo(metaData);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(CreateCitizenResponse.create()).hasAllNullFieldsOrProperties();
		assertThat(new CreateCitizenResponse()).hasAllNullFieldsOrProperties();
	}
}
