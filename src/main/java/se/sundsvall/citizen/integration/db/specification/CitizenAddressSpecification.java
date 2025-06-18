package se.sundsvall.citizen.integration.db.specification;

import static java.util.Objects.nonNull;

import java.time.OffsetDateTime;
import org.springframework.data.jpa.domain.Specification;
import se.sundsvall.citizen.integration.db.model.CitizenAddressEntity;

public interface CitizenAddressSpecification {

	static Specification<CitizenAddressEntity> hasChangedAddressSince(OffsetDateTime changedDateFrom) {
		return (addressEntity, cq, cb) -> nonNull(changedDateFrom) ? cb.greaterThanOrEqualTo(
			addressEntity.get("citizen").get("updatedAt"),
			changedDateFrom) : cb.and();
	}

	static Specification<CitizenAddressEntity> withStatus(String status) {
		return buildEqualFilter("status", status);
	}

	static Specification<CitizenAddressEntity> withCity(String city) {
		return buildEqualFilter("city", city);
	}

	static Specification<CitizenAddressEntity> withAddressType(String addressType) {
		return buildEqualFilter("addressType", addressType);
	}

	static Specification<CitizenAddressEntity> withPostalCode(String postalCode) {
		return buildEqualFilter("postalCode", postalCode);
	}

	/**
	 * Method builds an equal filter if value is not null. If value is null, method returns
	 * an always-true predicate (meaning no filtering will be applied for sent in attribute)
	 *
	 * @param  attribute name that will be used in filter
	 * @param  value     value (or null) to compare against
	 * @return           {@code Specification<CitizenAddressEntity>} matching sent in comparison
	 */
	private static Specification<CitizenAddressEntity> buildEqualFilter(String attribute, Object value) {
		return (addressEntity, cq, cb) -> nonNull(value) ? cb.equal(addressEntity.get(attribute), value) : cb.and();
	}
}
