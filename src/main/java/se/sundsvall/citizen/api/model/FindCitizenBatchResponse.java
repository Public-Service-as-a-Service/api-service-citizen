package se.sundsvall.citizen.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Objects;

@Schema(description = "FindCitizenBatchResponse model")
public class FindCitizenBatchResponse {

	@JsonProperty("_meta")
	@Schema(implementation = MetaData.class, accessMode = READ_ONLY)
	private MetaData metaData;

	@ArraySchema(schema = @Schema(implementation = CitizenExtended.class, accessMode = READ_ONLY))
	private List<CitizenExtended> citizens;

	public static FindCitizenBatchResponse create() {
		return new FindCitizenBatchResponse();
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public FindCitizenBatchResponse withMetaData(MetaData metaData) {
		this.metaData = metaData;
		return this;
	}

	public List<CitizenExtended> getCitizens() {
		return citizens;
	}

	public void setCitizens(List<CitizenExtended> citizens) {
		this.citizens = citizens;
	}

	public FindCitizenBatchResponse withCitizens(List<CitizenExtended> citizens) {
		this.citizens = citizens;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(citizens, metaData);
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
		FindCitizenBatchResponse other = (FindCitizenBatchResponse) obj;
		return Objects.equals(citizens, other.citizens) && Objects.equals(metaData, other.metaData);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCitizenBatchResponse [metaData=").append(metaData)
			.append(", citizens=").append(citizens)
			.append("]");
		return builder.toString();
	}
}
