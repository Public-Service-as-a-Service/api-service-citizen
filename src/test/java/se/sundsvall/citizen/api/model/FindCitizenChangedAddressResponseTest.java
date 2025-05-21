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

class FindCitizenChangedAddressResponseTest {

	@Test
	void testBean() {
		assertThat(FindCitizenChangedAddressResponse.class, allOf(
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

		final var citizen1 = CitizenWithChangedAddress.create()
			.withPersonId(UUID.randomUUID());
		final var citizen2 = CitizenWithChangedAddress.create()
			.withPersonId(UUID.randomUUID());
		final var citizens = List.of(citizen1, citizen2);

		final var findCitizenChangedAddressResponse = FindCitizenChangedAddressResponse.create()
			.withMetaData(metaData)
			.withCitizens(citizens);

		assertThat(findCitizenChangedAddressResponse).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(findCitizenChangedAddressResponse.getMetaData()).isEqualTo(metaData);
		assertThat(findCitizenChangedAddressResponse.getCitizens())
			.isEqualTo(citizens)
			.hasSize(2)
			.containsExactly(citizen1, citizen2);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(FindCitizenChangedAddressResponse.create()).hasAllNullFieldsOrProperties();
		assertThat(new FindCitizenChangedAddressResponse()).hasAllNullFieldsOrProperties();
	}
}
