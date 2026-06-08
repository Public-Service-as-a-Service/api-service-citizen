package se.sundsvall.citizen.integration.db.specification;

import org.springframework.data.jpa.domain.Specification;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;

import static java.util.Objects.nonNull;

public interface CitizenSpecification {

	static Specification<CitizenEntity> withPersonId(String personId) {
		return buildEqualFilter("personId", personId);
	}

	static Specification<CitizenEntity> withClassified(Boolean showClassified) {
		return (citizenEntity, cq, cb) -> {
			if (Boolean.TRUE.equals(showClassified)) {
				return cb.and(); // No filtering on classified status
			}
			return cb.isNull(citizenEntity.get("classified"));
		};
	}

	/**
	 * Method builds an equal filter if value is not null. If value is null, method returns
	 * an always-true predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           {@code Specification<CitizenEntity>} matching sent in comparison
	 */
	private static Specification<CitizenEntity> buildEqualFilter(String attribute, Object value) {
		return (citizenEntity, cq, cb) -> nonNull(value) ? cb.equal(citizenEntity.get(attribute), value) : cb.and();
	}
}
