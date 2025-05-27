package se.sundsvall.citizen.integration.db.model;

import static java.time.OffsetDateTime.now;
import static java.time.temporal.ChronoUnit.MILLIS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "custody_children_pupils",
	indexes = {
		@Index(name = "idx_custody_children_pupils_person_id", columnList = "person_id")
	})
public class CustodyChildrenPupilEntity implements Serializable {

	private static final long serialVersionUID = -3451441096651461592L;

	@Id
	@UuidGenerator
	@Column(name = "id")
	private String id;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private CitizenEntity citizen;

	@Column(name = "child_personal_number", length = 20)
	private String personnumber;

	@Column(name = "type_of_school", length = 100)
	private String typeOfSchool;

	@Column(name = "unreg_code", length = 50)
	private String unRegCode;

	@Column(name = "unreg_date")
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime unRegDate;

	@Column(name = "created_at", updatable = false)
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime createdAt;

	@Column(name = "updated_at")
	@TimeZoneStorage(TimeZoneStorageType.NORMALIZE)
	private OffsetDateTime updatedAt;

	public static CustodyChildrenPupilEntity create() {
		return new CustodyChildrenPupilEntity();
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

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public CustodyChildrenPupilEntity withId(final String id) {
		this.id = id;
		return this;
	}

	public CitizenEntity getCitizen() {
		return citizen;
	}

	public void setCitizen(final CitizenEntity citizen) {
		this.citizen = citizen;
	}

	public CustodyChildrenPupilEntity withCitizen(final CitizenEntity citizen) {
		this.citizen = citizen;
		return this;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(final String personnumber) {
		this.personnumber = personnumber;
	}

	public CustodyChildrenPupilEntity withPersonnumber(final String personnumber) {
		this.personnumber = personnumber;
		return this;
	}

	public String getTypeOfSchool() {
		return typeOfSchool;
	}

	public void setTypeOfSchool(final String typeOfSchool) {
		this.typeOfSchool = typeOfSchool;
	}

	public CustodyChildrenPupilEntity withTypeOfSchool(final String typeOfSchool) {
		this.typeOfSchool = typeOfSchool;
		return this;
	}

	public String getUnRegCode() {
		return unRegCode;
	}

	public void setUnRegCode(final String unRegCode) {
		this.unRegCode = unRegCode;
	}

	public CustodyChildrenPupilEntity withUnRegCode(final String unRegCode) {
		this.unRegCode = unRegCode;
		return this;
	}

	public OffsetDateTime getUnRegDate() {
		return unRegDate;
	}

	public void setUnRegDate(final OffsetDateTime unRegDate) {
		this.unRegDate = unRegDate;
	}

	public CustodyChildrenPupilEntity withUnRegDate(final OffsetDateTime unRegDate) {
		this.unRegDate = unRegDate;
		return this;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public CustodyChildrenPupilEntity withCreatedAt(final OffsetDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(final OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CustodyChildrenPupilEntity withUpdatedAt(final OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, citizen, personnumber, typeOfSchool, unRegCode,
			unRegDate, createdAt, updatedAt);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		final var other = (CustodyChildrenPupilEntity) obj;
		return Objects.equals(id, other.id) &&
			Objects.equals(citizen, other.citizen) &&
			Objects.equals(personnumber, other.personnumber) &&
			Objects.equals(typeOfSchool, other.typeOfSchool) &&
			Objects.equals(unRegCode, other.unRegCode) &&
			Objects.equals(unRegDate, other.unRegDate) &&
			Objects.equals(createdAt, other.createdAt) &&
			Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		final var builder = new StringBuilder();
		builder.append("CustodyChildrenPupilEntity [id=").append(id)
			.append(", citizen=").append(citizen != null ? citizen.getPersonId() : null)
			.append(", personnumber=").append(personnumber)
			.append(", typeOfSchool=").append(typeOfSchool)
			.append(", unRegCode=").append(unRegCode)
			.append(", unRegDate=").append(unRegDate)
			.append(", createdAt=").append(createdAt)
			.append(", updatedAt=").append(updatedAt)
			.append("]");
		return builder.toString();
	}
}
