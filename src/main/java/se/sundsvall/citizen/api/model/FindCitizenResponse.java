package se.sundsvall.citizen.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "FindCitizenResponse model")
public class FindCitizenResponse {

	@JsonProperty("_meta")
	@Schema(implementation = MetaData.class, accessMode = READ_ONLY)
	private MetaData metaData;

	@Schema(implementation = CitizenExtended.class, accessMode = READ_ONLY)
	private CitizenExtended citizen;

	public static FindCitizenResponse create() {
		return new FindCitizenResponse();
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public FindCitizenResponse withMetaData(MetaData metaData) {
		this.metaData = metaData;
		return this;
	}

	public CitizenExtended getCitizen() {
		return citizen;
	}

	public void setCitizen(CitizenExtended citizen) {
		this.citizen = citizen;
	}

	public FindCitizenResponse withCitizen(CitizenExtended citizen) {
		this.citizen = citizen;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(citizen, metaData);
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
		FindCitizenResponse other = (FindCitizenResponse) obj;
		return Objects.equals(citizen, other.citizen) && Objects.equals(metaData, other.metaData);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCitizenResponse [metaData=").append(metaData)
			.append(", citizen=").append(citizen)
			.append("]");
		return builder.toString();
	}
}
