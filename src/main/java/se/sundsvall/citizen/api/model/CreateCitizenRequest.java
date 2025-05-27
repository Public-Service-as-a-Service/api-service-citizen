package se.sundsvall.citizen.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Schema(description = "CreateCitizenRequest model")
public class CreateCitizenRequest {

	@Schema(description = "Personal number for the citizen", example = "198001011234", nullable = true)
	@Size(max = 20)
	private String personalNumber;

	public static CreateCitizenRequest create() {
		return new CreateCitizenRequest();
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public CreateCitizenRequest withPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personalNumber);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final var other = (CreateCitizenRequest) obj;
		return Objects.equals(personalNumber, other.personalNumber);
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("CreateCitizenRequest [personalNumber=").append(personalNumber)
			.append("]").toString();
	}
}
