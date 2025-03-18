package se.sundsvall.citizen.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import se.sundsvall.dept44.common.validators.annotation.ValidUuid;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Schema(description = "FindCitizenBatchRequest model")
public class FindCitizenBatchRequest {
    private static final String DEFAULT_PAGE = "1";
    private static final String DEFAULT_LIMIT = "100";

    @Schema(description = "List of person IDs to fetch", example = "[\"81471222-5798-11e9-ae24-57fa13b361e1\"]")
    @NotEmpty
    private List<@ValidUuid UUID> personIds;

    @Schema(description = "Include search for classified persons", example = "false", defaultValue = "false")
    private boolean showClassified = false;

    @Schema(description = "Page number", example = DEFAULT_PAGE, defaultValue = DEFAULT_PAGE)
    @Min(1)
    protected int page = parseInt(DEFAULT_PAGE);

    @Schema(description = "Result size per page", example = DEFAULT_LIMIT, defaultValue = DEFAULT_LIMIT)
    @Min(1)
    @Max(1000)
    protected int limit = parseInt(DEFAULT_LIMIT);

    public static FindCitizenBatchRequest create() {
        return new FindCitizenBatchRequest();
    }

    public List<UUID> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<UUID> personIds) {
        this.personIds = personIds;
    }

    public FindCitizenBatchRequest withPersonIds(List<UUID> personIds) {
        this.personIds = personIds;
        return this;
    }

    public boolean isShowClassified() {
        return showClassified;
    }

    public void setShowClassified(boolean showClassified) {
        this.showClassified = showClassified;
    }

    public FindCitizenBatchRequest withShowClassified(boolean showClassified) {
        this.showClassified = showClassified;
        return this;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public FindCitizenBatchRequest withPage(int page) {
        this.page = page;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public FindCitizenBatchRequest withLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personIds, showClassified, limit, page);
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
        FindCitizenBatchRequest other = (FindCitizenBatchRequest) obj;
        return Objects.equals(personIds, other.personIds) &&
                showClassified == other.showClassified &&
                limit == other.limit &&
                page == other.page;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("FindCitizenBatchRequest [personIds=").append(personIds)
                .append(", showClassified=").append(showClassified)
                .append(", page=").append(page)
                .append(", limit=").append(limit)
                .append("]").toString();
    }
}