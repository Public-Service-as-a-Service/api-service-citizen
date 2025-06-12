package se.sundsvall.citizen.integration.db;

import static se.sundsvall.citizen.integration.db.specification.CitizenSpecification.withClassified;
import static se.sundsvall.citizen.integration.db.specification.CitizenSpecification.withPersonId;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import se.sundsvall.citizen.integration.db.model.CitizenEntity;

@CircuitBreaker(name = "CitizenRepository")
public interface CitizenRepository extends JpaRepository<CitizenEntity, String>, JpaSpecificationExecutor<CitizenEntity> {

	default Page<CitizenEntity> findAllByParameters(final String personId, final Pageable pageable, final boolean showClassified) {
		return this.findAll(withPersonId(personId)
			.and(withClassified(showClassified)), pageable);
	}

	Optional<CitizenEntity> findByPersonalNumber(String personalNumber);
}
