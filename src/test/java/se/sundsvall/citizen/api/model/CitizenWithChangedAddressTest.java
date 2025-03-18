package se.sundsvall.citizen.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class CitizenWithChangedAddressTest {

    @Test
    void testBean() {
        assertThat(CitizenWithChangedAddress.class, allOf(
                hasValidBeanConstructor(),
                hasValidGettersAndSetters(),
                hasValidBeanHashCode(),
                hasValidBeanEquals(),
                hasValidBeanToString()));
    }

    @Test
    void testBuilderMethods() {
        final var personId = UUID.randomUUID();
        final var personNumber = "personNumber";
        final var classified = "classified";
        final var unRegCode = "unRegCode";
        final var unRegDate = "2024-01-29";
        final var gender = "gender";
        final var givenname = "givenname";
        final var lastname = "lastname";
        final var typeOfSchool = "typeOfSchool";
        final var addresses = List.of(CitizenAddress.create()
                .withAddress("testAddress")
                .withCity("testCity"));

        final var citizenWithChangedAddress = CitizenWithChangedAddress.create()
                .withPersonId(personId)
                .withPersonNumber(personNumber)
                .withClassified(classified)
                .withUnRegCode(unRegCode)
                .withUnRegDate(unRegDate)
                .withGender(gender)
                .withGivenname(givenname)
                .withLastname(lastname)
                .withTypeOfSchool(typeOfSchool)
                .withAddresses(addresses);

        assertThat(citizenWithChangedAddress).isNotNull().hasNoNullFieldsOrProperties();
        assertThat(citizenWithChangedAddress.getPersonId()).isEqualTo(personId);
        assertThat(citizenWithChangedAddress.getPersonNumber()).isEqualTo(personNumber);
        assertThat(citizenWithChangedAddress.getClassified()).isEqualTo(classified);
        assertThat(citizenWithChangedAddress.getUnRegCode()).isEqualTo(unRegCode);
        assertThat(citizenWithChangedAddress.getUnRegDate()).isEqualTo(unRegDate);
        assertThat(citizenWithChangedAddress.getGender()).isEqualTo(gender);
        assertThat(citizenWithChangedAddress.getGivenname()).isEqualTo(givenname);
        assertThat(citizenWithChangedAddress.getLastname()).isEqualTo(lastname);
        assertThat(citizenWithChangedAddress.getTypeOfSchool()).isEqualTo(typeOfSchool);
        assertThat(citizenWithChangedAddress.getAddresses()).isEqualTo(addresses);
    }

    @Test
    void testNoDirtOnCreatedBean() {
        assertThat(CitizenWithChangedAddress.create()).hasAllNullFieldsOrProperties();
    }
}