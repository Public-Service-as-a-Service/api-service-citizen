package se.sundsvall.citizen.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "FindPersonGuidBatchResponse model")
public class FindPersonGuidBatchResponse {

    @JsonProperty("_meta")
    @Schema(implementation = MetaData.class, accessMode = READ_ONLY)
    private MetaData metaData;

    @ArraySchema(schema = @Schema(implementation = PersonGuidBatch.class, accessMode = READ_ONLY))
    private List<PersonGuidBatch> results;

    public static FindPersonGuidBatchResponse create() {
        return new FindPersonGuidBatchResponse();
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public FindPersonGuidBatchResponse withMetaData(MetaData metaData) {
        this.metaData = metaData;
        return this;
    }

    public List<PersonGuidBatch> getResults() {
        return results;
    }

    public void setResults(List<PersonGuidBatch> results) {
        this.results = results;
    }

    public FindPersonGuidBatchResponse withResults(List<PersonGuidBatch> results) {
        this.results = results;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, metaData);
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
        FindPersonGuidBatchResponse other = (FindPersonGuidBatchResponse) obj;
        return Objects.equals(results, other.results) && Objects.equals(metaData, other.metaData);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FindPersonGuidBatchResponse [metaData=").append(metaData)
                .append(", results=").append(results)
                .append("]");
        return builder.toString();
    }
}