package se.sundsvall.citizen.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@Schema(description = "Model for posting a new person")
public class ModelPostPerson {

	@Schema(description = "Personal identity number for the person", example = "198001011234")
	private String personalNumber;

	public ModelPostPerson() {}

	public ModelPostPerson(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public ModelPostPerson withPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ModelPostPerson that = (ModelPostPerson) o;
		return Objects.equals(personalNumber, that.personalNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personalNumber);
	}

	@Override
	public String toString() {
		return "ModelPostPerson{" +
			"personalNumber='" + personalNumber + '\'' +
			'}';
	}
}
