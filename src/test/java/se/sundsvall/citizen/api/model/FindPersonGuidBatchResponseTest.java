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

class FindPersonGuidBatchResponseTest {

    @Test
    void testBean() {
        assertThat(FindPersonGuidBatchResponse.class, allOf(
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

        final var result1 = PersonGuidBatch.create()
                .withPersonNumber("198001011234")
                .withPersonId(UUID.randomUUID())
                .withSuccess(true);
        final var result2 = PersonGuidBatch.create()
                .withPersonNumber("198001011235")
                .withPersonId(UUID.randomUUID())
                .withSuccess(true);
        final var results = List.of(result1, result2);

        final var findPersonGuidBatchResponse = FindPersonGuidBatchResponse.create()
                .withMetaData(metaData)
                .withResults(results);

        assertThat(findPersonGuidBatchResponse).isNotNull().hasNoNullFieldsOrProperties();
        assertThat(findPersonGuidBatchResponse.getMetaData()).isEqualTo(metaData);
        assertThat(findPersonGuidBatchResponse.getResults())
                .isEqualTo(results)
                .hasSize(2)
                .containsExactly(result1, result2);
    }

    @Test
    void testNoDirtOnCreatedBean() {
        assertThat(FindPersonGuidBatchResponse.create()).hasAllNullFieldsOrProperties();
        assertThat(new FindPersonGuidBatchResponse()).hasAllNullFieldsOrProperties();
    }
}