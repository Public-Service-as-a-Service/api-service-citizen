package se.sundsvall.citizen.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@Schema(description = "Citizen with changed address model")
public class CitizenWithChangedAddress {

    @Schema(description = "Person ID", example = "b82bd8ac-1507-4d9a-958d-369261eecc15")
    private UUID personId;

    @Schema(description = "Personal number", nullable = true)
    private String personNumber;

    @Schema(description = "Classification status", nullable = true)
    private String classified;

    @Schema(description = "Unregistration code", nullable = true)
    private String unRegCode;

    @Schema(description = "Unregistration date", nullable = true)
    private String unRegDate;

    @Schema(description = "Gender", nullable = true)
    private String gender;

    @Schema(description = "Given name", nullable = true)
    private String givenname;

    @Schema(description = "Last name", nullable = true)
    private String lastname;

    @Schema(description = "Type of school", nullable = true)
    private String typeOfSchool;

    @Schema(description = "Addresses", nullable = true)
    private List<CitizenAddress> addresses;

    public static CitizenWithChangedAddress create() {
        return new CitizenWithChangedAddress();
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public CitizenWithChangedAddress withPersonId(UUID personId) {
        this.personId = personId;
        return this;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }

    public CitizenWithChangedAddress withPersonNumber(String personNumber) {
        this.personNumber = personNumber;
        return this;
    }

    public String getClassified() {
        return classified;
    }

    public void setClassified(String classified) {
        this.classified = classified;
    }

    public CitizenWithChangedAddress withClassified(String classified) {
        this.classified = classified;
        return this;
    }

    public String getUnRegCode() {
        return unRegCode;
    }

    public void setUnRegCode(String unRegCode) {
        this.unRegCode = unRegCode;
    }

    public CitizenWithChangedAddress withUnRegCode(String unRegCode) {
        this.unRegCode = unRegCode;
        return this;
    }

    public String getUnRegDate() {
        return unRegDate;
    }

    public void setUnRegDate(String unRegDate) {
        this.unRegDate = unRegDate;
    }

    public CitizenWithChangedAddress withUnRegDate(String unRegDate) {
        this.unRegDate = unRegDate;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public CitizenWithChangedAddress withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getGivenname() {
        return givenname;
    }

    public void setGivenname(String givenname) {
        this.givenname = givenname;
    }

    public CitizenWithChangedAddress withGivenname(String givenname) {
        this.givenname = givenname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public CitizenWithChangedAddress withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getTypeOfSchool() {
        return typeOfSchool;
    }

    public void setTypeOfSchool(String typeOfSchool) {
        this.typeOfSchool = typeOfSchool;
    }

    public CitizenWithChangedAddress withTypeOfSchool(String typeOfSchool) {
        this.typeOfSchool = typeOfSchool;
        return this;
    }

    public List<CitizenAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<CitizenAddress> addresses) {
        this.addresses = addresses;
    }

    public CitizenWithChangedAddress withAddresses(List<CitizenAddress> addresses) {
        this.addresses = addresses;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, personNumber, classified, unRegCode, unRegDate, gender,
                givenname, lastname, typeOfSchool, addresses);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CitizenWithChangedAddress other = (CitizenWithChangedAddress) obj;
        return Objects.equals(personId, other.personId) &&
                Objects.equals(personNumber, other.personNumber) &&
                Objects.equals(classified, other.classified) &&
                Objects.equals(unRegCode, other.unRegCode) &&
                Objects.equals(unRegDate, other.unRegDate) &&
                Objects.equals(gender, other.gender) &&
                Objects.equals(givenname, other.givenname) &&
                Objects.equals(lastname, other.lastname) &&
                Objects.equals(typeOfSchool, other.typeOfSchool) &&
                Objects.equals(addresses, other.addresses);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("CitizenWithChangedAddress [personId=").append(personId)
                .append(", personNumber=").append(personNumber)
                .append(", classified=").append(classified)
                .append(", unRegCode=").append(unRegCode)
                .append(", unRegDate=").append(unRegDate)
                .append(", gender=").append(gender)
                .append(", givenname=").append(givenname)
                .append(", lastname=").append(lastname)
                .append(", typeOfSchool=").append(typeOfSchool)
                .append(", addresses=").append(addresses)
                .append("]").toString();
    }
}