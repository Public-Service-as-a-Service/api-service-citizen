package se.sundsvall.citizen.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import java.util.UUID;

@Schema(description = "Person GUID batch response model")
public class PersonGuidBatch {

	@Schema(description = "Personal number", nullable = true)
	private String personNumber;

	@Schema(description = "Person ID", nullable = true)
	private UUID personId;

	@Schema(description = "Success status")
	private boolean success;

	@Schema(description = "Error message if operation failed", nullable = true)
	private String errorMessage;

	public static PersonGuidBatch create() {
		return new PersonGuidBatch();
	}

	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
	}

	public PersonGuidBatch withPersonNumber(String personNumber) {
		this.personNumber = personNumber;
		return this;
	}

	public UUID getPersonId() {
		return personId;
	}

	public void setPersonId(UUID personId) {
		this.personId = personId;
	}

	public PersonGuidBatch withPersonId(UUID personId) {
		this.personId = personId;
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public PersonGuidBatch withSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public PersonGuidBatch withErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personNumber, personId, success, errorMessage);
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
		PersonGuidBatch other = (PersonGuidBatch) obj;
		return Objects.equals(personNumber, other.personNumber) &&
			Objects.equals(personId, other.personId) &&
			success == other.success &&
			Objects.equals(errorMessage, other.errorMessage);
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("PersonGuidBatch [personNumber=").append(personNumber)
			.append(", personId=").append(personId)
			.append(", success=").append(success)
			.append(", errorMessage=").append(errorMessage)
			.append("]").toString();
	}
}
