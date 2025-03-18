package se.sundsvall.citizen.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class CitizenAddressTest {

    @Test
    void testBean() {
        assertThat(CitizenAddress.class, allOf(
                hasValidBeanConstructor(),
                hasValidGettersAndSetters(),
                hasValidBeanHashCode(),
                hasValidBeanEquals()));
    }

    @Test
    void testBuilderMethods() {
        final var status = "status";
        final var nrDate = "2024-01-29";
        final var realEstateDescription = "realEstateDescription";
        final var co = "co";
        final var address = "address";
        final var addressArea = "addressArea";
        final var addressNumber = "addressNumber";
        final var addressLetter = "addressLetter";
        final var apartmentNumber = "apartmentNumber";
        final var postalCode = "postalCode";
        final var city = "city";
        final var county = "county";
        final var municipality = "municipality";
        final var country = "country";
        final var emigrated = true;
        final var addressType = "addressType";
        final var xCoordLocal = 123.456;
        final var yCoordLocal = 789.012;

        final var citizenAddress = CitizenAddress.create()
                .withStatus(status)
                .withNrDate(nrDate)
                .withRealEstateDescription(realEstateDescription)
                .withCo(co)
                .withAddress(address)
                .withAddressArea(addressArea)
                .withAddressNumber(addressNumber)
                .withAddressLetter(addressLetter)
                .withApartmentNumber(apartmentNumber)
                .withPostalCode(postalCode)
                .withCity(city)
                .withCounty(county)
                .withMunicipality(municipality)
                .withCountry(country)
                .withEmigrated(emigrated)
                .withAddressType(addressType)
                .withXCoordLocal(xCoordLocal)
                .withYCoordLocal(yCoordLocal);

        assertThat(citizenAddress).isNotNull().hasNoNullFieldsOrProperties();
        assertThat(citizenAddress.getStatus()).isEqualTo(status);
        assertThat(citizenAddress.getNrDate()).isEqualTo(nrDate);
        assertThat(citizenAddress.getRealEstateDescription()).isEqualTo(realEstateDescription);
        assertThat(citizenAddress.getCo()).isEqualTo(co);
        assertThat(citizenAddress.getAddress()).isEqualTo(address);
        assertThat(citizenAddress.getAddressArea()).isEqualTo(addressArea);
        assertThat(citizenAddress.getAddressNumber()).isEqualTo(addressNumber);
        assertThat(citizenAddress.getAddressLetter()).isEqualTo(addressLetter);
        assertThat(citizenAddress.getApartmentNumber()).isEqualTo(apartmentNumber);
        assertThat(citizenAddress.getPostalCode()).isEqualTo(postalCode);
        assertThat(citizenAddress.getCity()).isEqualTo(city);
        assertThat(citizenAddress.getCounty()).isEqualTo(county);
        assertThat(citizenAddress.getMunicipality()).isEqualTo(municipality);
        assertThat(citizenAddress.getCountry()).isEqualTo(country);
        assertThat(citizenAddress.getEmigrated()).isEqualTo(emigrated);
        assertThat(citizenAddress.getAddressType()).isEqualTo(addressType);
        assertThat(citizenAddress.getXCoordLocal()).isEqualTo(xCoordLocal);
        assertThat(citizenAddress.getYCoordLocal()).isEqualTo(yCoordLocal);
    }

    @Test
    void testNoDirtOnCreatedBean() {
        assertThat(CitizenAddress.create()).hasAllNullFieldsOrProperties();
    }
}