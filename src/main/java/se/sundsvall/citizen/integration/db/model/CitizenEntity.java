package se.sundsvall.citizen.integration.db.model;

import static java.time.OffsetDateTime.now;
import static java.time.temporal.ChronoUnit.MILLIS;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "citizens",
	indexes = {
		@Index(name = "idx_citizens_personal_number", columnList = "personal_number", unique = true)
	})
public class CitizenEntity implements Serializable {

	private static final long serialVersionUID = -4567890123456789L;

	@Id
	@UuidGenerator
	@Column(name = "person_id")
	private String personId;

	@Column(name = "personal_number", length = 20, unique = true)
	private String personalNumber;

	@Column(name = "givenname", length = 100)
	private String givenname;

	@Column(name = "lastname", length = 100)
	private String lastname;

	@Column(name = "gender", length = 10)
	private String gender;

	@Column(name = "civil_status", length = 20)
	private String civilStatus;

	@Column(name = "nr_date")
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime nrDate;

	@Column(name = "classified", length = 5000)
	private String classified;

	@Column(name = "protected_nr", length = 50)
	private String protectedNr;

	@Column(name = "created_at", updatable = false)
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime createdAt;

	@Column(name = "updated_at")
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime updatedAt;

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CitizenAddressEntity> addresses = new ArrayList<>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustodyChildrenPupilEntity> custodyChildren = new ArrayList<>();

	public static CitizenEntity create() {
		return new CitizenEntity();
	}

	@PrePersist
	void prePersist() {
		createdAt = now(ZoneId.systemDefault()).truncatedTo(MILLIS);
		updatedAt = createdAt;
	}

	@PreUpdate
	void preUpdate() {
		updatedAt = now(ZoneId.systemDefault()).truncatedTo(MILLIS);
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(final String personId) {
		this.personId = personId;
	}

	public CitizenEntity withPersonId(final String personId) {
		this.personId = personId;
		return this;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(final String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public CitizenEntity withPersonalNumber(final String personalNumber) {
		this.personalNumber = personalNumber;
		return this;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(final String givenname) {
		this.givenname = givenname;
	}

	public CitizenEntity withGivenname(final String givenname) {
		this.givenname = givenname;
		return this;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}

	public CitizenEntity withLastname(final String lastname) {
		this.lastname = lastname;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public CitizenEntity withGender(final String gender) {
		this.gender = gender;
		return this;
	}

	public String getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(final String civilStatus) {
		this.civilStatus = civilStatus;
	}

	public CitizenEntity withCivilStatus(final String civilStatus) {
		this.civilStatus = civilStatus;
		return this;
	}

	public OffsetDateTime getNrDate() {
		return nrDate;
	}

	public void setNrDate(final OffsetDateTime nrDate) {
		this.nrDate = nrDate;
	}

	public CitizenEntity withNrDate(final OffsetDateTime nrDate) {
		this.nrDate = nrDate;
		return this;
	}

	public String getClassified() {
		return classified;
	}

	public void setClassified(final String classified) {
		this.classified = classified;
	}

	public CitizenEntity withClassified(final String classified) {
		this.classified = classified;
		return this;
	}

	public String getProtectedNr() {
		return protectedNr;
	}

	public void setProtectedNr(final String protectedNr) {
		this.protectedNr = protectedNr;
	}

	public CitizenEntity withProtectedNr(final String protectedNr) {
		this.protectedNr = protectedNr;
		return this;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public CitizenEntity withCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(final OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CitizenEntity withUpdatedAt(final OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	public List<CitizenAddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(final List<CitizenAddressEntity> addresses) {
		this.addresses = addresses;
	}

	public CitizenEntity withAddresses(final List<CitizenAddressEntity> addresses) {
		this.addresses = addresses;
		return this;
	}

	public List<CustodyChildrenPupilEntity> getCustodyChildren() {
		return custodyChildren;
	}

	public void setCustodyChildren(final List<CustodyChildrenPupilEntity> custodyChildren) {
		this.custodyChildren = custodyChildren;
	}

	public CitizenEntity withCustodyChildren(final List<CustodyChildrenPupilEntity> custodyChildren) {
		this.custodyChildren = custodyChildren;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, personalNumber, givenname, lastname, gender, civilStatus, nrDate,
			classified, protectedNr, createdAt, updatedAt, addresses, custodyChildren);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		final var other = (CitizenEntity) obj;
		return Objects.equals(personId, other.personId) &&
			Objects.equals(personalNumber, other.personalNumber) &&
			Objects.equals(givenname, other.givenname) &&
			Objects.equals(lastname, other.lastname) &&
			Objects.equals(gender, other.gender) &&
			Objects.equals(civilStatus, other.civilStatus) &&
			Objects.equals(nrDate, other.nrDate) &&
			Objects.equals(classified, other.classified) &&
			Objects.equals(protectedNr, other.protectedNr) &&
			Objects.equals(createdAt, other.createdAt) &&
			Objects.equals(updatedAt, other.updatedAt) &&
			Objects.equals(addresses, other.addresses) &&
			Objects.equals(custodyChildren, other.custodyChildren);
	}

	@Override
	public String toString() {
		final var builder = new StringBuilder();
		builder.append("CitizenEntity [personId=").append(personId)
			.append(", personalNumber=").append(personalNumber)
			.append(", givenname=").append(givenname)
			.append(", lastname=").append(lastname)
			.append(", gender=").append(gender)
			.append(", civilStatus=").append(civilStatus)
			.append(", nrDate=").append(nrDate)
			.append(", classified=").append(classified)
			.append(", protectedNr=").append(protectedNr)
			.append(", createdAt=").append(createdAt)
			.append(", updatedAt=").append(updatedAt)
			.append(", addresses=").append(addresses)
			.append(", custodyChildren=").append(custodyChildren)
			.append("]");
		return builder.toString();
	}
}
