package se.sundsvall.citizen.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "FindCitizenChangedAddressResponse model")
public class FindCitizenChangedAddressResponse {

	@JsonProperty("_meta")
	@Schema(implementation = MetaData.class, accessMode = READ_ONLY)
	private MetaData metaData;

	@ArraySchema(schema = @Schema(implementation = CitizenWithChangedAddress.class, accessMode = READ_ONLY))
	private List<CitizenWithChangedAddress> citizens;

	public static FindCitizenChangedAddressResponse create() {
		return new FindCitizenChangedAddressResponse();
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public FindCitizenChangedAddressResponse withMetaData(MetaData metaData) {
		this.metaData = metaData;
		return this;
	}

	public List<CitizenWithChangedAddress> getCitizens() {
		return citizens;
	}

	public void setCitizens(List<CitizenWithChangedAddress> citizens) {
		this.citizens = citizens;
	}

	public FindCitizenChangedAddressResponse withCitizens(List<CitizenWithChangedAddress> citizens) {
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
		FindCitizenChangedAddressResponse other = (FindCitizenChangedAddressResponse) obj;
		return Objects.equals(citizens, other.citizens) && Objects.equals(metaData, other.metaData);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FindCitizenChangedAddressResponse [metaData=").append(metaData)
			.append(", citizens=").append(citizens)
			.append("]");
		return builder.toString();
	}
}
