package se.sundsvall.citizen.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

@Schema(description = "FindPersonGuidBatchRequest model")
public class FindPersonGuidBatchRequest {
	private static final String DEFAULT_PAGE = "1";
	private static final String DEFAULT_LIMIT = "100";

	@Schema(description = "List of personal numbers to fetch", example = "[\"198001011234\"]")
	@NotEmpty
	private List<@Size(max = 20) String> personalNumbers;

	@Schema(description = "Page number", example = DEFAULT_PAGE, defaultValue = DEFAULT_PAGE)
	@Min(1)
	protected int page = parseInt(DEFAULT_PAGE);

	@Schema(description = "Result size per page", example = DEFAULT_LIMIT, defaultValue = DEFAULT_LIMIT)
	@Min(1)
	@Max(1000)
	protected int limit = parseInt(DEFAULT_LIMIT);

	public static FindPersonGuidBatchRequest create() {
		return new FindPersonGuidBatchRequest();
	}

	public List<String> getPersonalNumbers() {
		return personalNumbers;
	}

	public void setPersonalNumbers(List<String> personalNumbers) {
		this.personalNumbers = personalNumbers;
	}

	public FindPersonGuidBatchRequest withPersonalNumbers(List<String> personalNumbers) {
		this.personalNumbers = personalNumbers;
		return this;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public FindPersonGuidBatchRequest withPage(int page) {
		this.page = page;
		return this;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public FindPersonGuidBatchRequest withLimit(int limit) {
		this.limit = limit;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(personalNumbers, limit, page);
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
		FindPersonGuidBatchRequest other = (FindPersonGuidBatchRequest) obj;
		return Objects.equals(personalNumbers, other.personalNumbers) &&
			limit == other.limit &&
			page == other.page;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("FindPersonGuidBatchRequest [personalNumbers=").append(personalNumbers)
			.append(", page=").append(page)
			.append(", limit=").append(limit)
			.append("]").toString();
	}
}
