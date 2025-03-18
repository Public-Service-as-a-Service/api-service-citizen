package se.sundsvall.citizen.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

class CitizenExtendedTest {

    @Test
    void testBean() {
        assertThat(CitizenExtended.class, allOf(
                hasValidBeanConstructor(),
                hasValidGettersAndSetters(),
                hasValidBeanHashCode(),
                hasValidBeanEquals(),
                hasValidBeanToString()));
    }

    @Test
    void testBuilderMethods() {
        final var personId = UUID.randomUUID();
        final var givenname = "Test";
        final var lastname = "Testsson";
        final var gender = "MALE";
        final var civilStatus = "MARRIED";
        final var nrDate = "2024-01-28T12:00:00Z";
        final var personalNumber = "20000101-1212";
        final var classified = "PROTECTED";
        final var protectedNr = "PROTECTED_IDENTITY";
        final var addresses = new ArrayList<CitizenAddress>();

        final var citizenExtended = CitizenExtended.create()
                .withPersonId(personId)
                .withGivenname(givenname)
                .withLastname(lastname)
                .withGender(gender)
                .withCivilStatus(civilStatus)
                .withNrDate(nrDate)
                .withPersonalNumber(personalNumber)
                .withClassified(classified)
                .withProtectedNr(protectedNr)
                .withAddresses(addresses);

        assertThat(citizenExtended).isNotNull().hasNoNullFieldsOrProperties();
        assertThat(citizenExtended.getPersonId()).isEqualTo(personId);
        assertThat(citizenExtended.getGivenname()).isEqualTo(givenname);
        assertThat(citizenExtended.getLastname()).isEqualTo(lastname);
        assertThat(citizenExtended.getGender()).isEqualTo(gender);
        assertThat(citizenExtended.getCivilStatus()).isEqualTo(civilStatus);
        assertThat(citizenExtended.getNrDate()).isEqualTo(nrDate);
        assertThat(citizenExtended.getPersonalNumber()).isEqualTo(personalNumber);
        assertThat(citizenExtended.getClassified()).isEqualTo(classified);
        assertThat(citizenExtended.getProtectedNr()).isEqualTo(protectedNr);
        assertThat(citizenExtended.getAddresses()).isEqualTo(addresses);
    }

    @Test
    void testNoDirtOnCreatedBean() {
        assertThat(CitizenExtended.create()).hasAllNullFieldsOrProperties();
        assertThat(new CitizenExtended()).hasAllNullFieldsOrProperties();
    }
}