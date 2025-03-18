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

class FindCitizenResponseTest {

    @Test
    void testBean() {
        assertThat(FindCitizenResponse.class, allOf(
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
                .withCount(1)
                .withTotalPages(1)
                .withTotalRecords(1);

        final var citizen = CitizenExtended.create()
                .withPersonId(UUID.randomUUID());

        final var findCitizenResponse = FindCitizenResponse.create()
                .withMetaData(metaData)
                .withCitizen(citizen);

        assertThat(findCitizenResponse).isNotNull().hasNoNullFieldsOrProperties();
        assertThat(findCitizenResponse.getMetaData()).isEqualTo(metaData);
        assertThat(findCitizenResponse.getCitizen())
                .isEqualTo(citizen)
                .isNotNull();
    }

    @Test
    void testNoDirtOnCreatedBean() {
        assertThat(FindCitizenResponse.create()).hasAllNullFieldsOrProperties();
        assertThat(new FindCitizenResponse()).hasAllNullFieldsOrProperties();
    }
}