package se.sundsvall.citizen.integration.db;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import se.sundsvall.citizen.integration.db.model.CitizenAddressEntity;

@CircuitBreaker(name = "CitizenAddressRepository")
public interface CitizenAddressRepository extends JpaRepository<CitizenAddressEntity, String>,
        JpaSpecificationExecutor<CitizenAddressEntity> {
}