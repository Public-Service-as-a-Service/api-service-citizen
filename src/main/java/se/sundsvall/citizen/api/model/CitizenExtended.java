package se.sundsvall.citizen.api.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(NON_NULL)
@Schema(description = "Citizen model")
public class CitizenExtended {

	@Schema(description = "Person ID (UUID)", example = "b82bd8ac-1507-4d9a-958d-369261eecc15")
	private UUID personId;

	@Schema(description = "Given name (förnamn)", example = "Anders")
	private String givenname;

	@Schema(description = "Last name (efternamn)", example = "Andersson")
	private String lastname;

	@Schema(description = "Gender", example = "FEMALE")
	private String gender;

	@Schema(description = "Civil status", example = "MARRIED")
	private String civilStatus;

	@Schema(description = "NR date")
	private String nrDate;

	@Schema(description = "Personal Number", example = "20001212-1212")
	private String personalNumber;

	@Schema(description = "Classification status", example = "PROTECTED")
	private String classified;

	@Schema(description = "Protection level", example = "PROTECTED_IDENTITY")
	private String protectedNr;

	@Schema(description = "Addresses")
	private List<CitizenAddress> addresses;

	public static CitizenExtended create() {
		return new CitizenExtended();
	}

	public UUID getPersonId() {
		return personId;
	}

	public void setPersonId(UUID personId) {
		this.personId = personId;
	}

	public CitizenExtended withPersonId(UUID personId) {
		this.personId = personId;
		return this;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public CitizenExtended withGivenname(String givenname) {
		this.givenname = givenname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public CitizenExtended withLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public CitizenExtended withGender(String gender) {
		this.gender = gender;
		return this;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public CitizenExtended withCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
		return this;
	}

	public String getNrDate() {
		return nrDate;
	}

	public void setNrDate(String nrDate) {
		this.nrDate = nrDate;
	}

	public CitizenExtended withNrDate(String nrDate) {
		this.nrDate = nrDate;
		return this;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public CitizenExtended withPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
		return this;
	}

	public String getClassified() {
		return classified;
	}

	public void setClassified(String classified) {
		this.classified = classified;
	}

	public CitizenExtended withClassified(String classified) {
		this.classified = classified;
		return this;
	}

	public String getProtectedNr() {
		return protectedNr;
	}

	public void setProtectedNr(String protectedNr) {
		this.protectedNr = protectedNr;
	}

	public CitizenExtended withProtectedNr(String protectedNr) {
		this.protectedNr = protectedNr;
		return this;
	}

	public List<CitizenAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CitizenAddress> addresses) {
		this.addresses = addresses;
	}

	public CitizenExtended withAddresses(List<CitizenAddress> addresses) {
		this.addresses = addresses;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, givenname, lastname, gender, civilStatus,
			nrDate, personalNumber, classified, protectedNr, addresses);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CitizenExtended other = (CitizenExtended) obj;
		return Objects.equals(personId, other.personId) &&
			Objects.equals(givenname, other.givenname) &&
			Objects.equals(lastname, other.lastname) &&
			Objects.equals(gender, other.gender) &&
			Objects.equals(civilStatus, other.civilStatus) &&
			Objects.equals(nrDate, other.nrDate) &&
			Objects.equals(personalNumber, other.personalNumber) &&
			Objects.equals(classified, other.classified) &&
			Objects.equals(protectedNr, other.protectedNr) &&
			Objects.equals(addresses, other.addresses);
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("CitizenExtended [personId=").append(personId)
			.append(", givenname=").append(givenname)
			.append(", lastname=").append(lastname)
			.append(", gender=").append(gender)
			.append(", civilStatus=").append(civilStatus)
			.append(", nrDate=").append(nrDate)
			.append(", personalNumber=").append(personalNumber)
			.append(", classified=").append(classified)
			.append(", protectedNr=").append(protectedNr)
			.append(", addresses=").append(addresses)
			.append("]").toString();
	}
}
