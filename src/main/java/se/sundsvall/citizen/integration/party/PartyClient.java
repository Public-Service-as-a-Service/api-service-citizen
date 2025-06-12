package se.sundsvall.citizen.integration.party;

import static se.sundsvall.citizen.integration.party.configuration.PartyIntegrationConfiguration.INTEGRATION_NAME;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.sundsvall.citizen.integration.party.configuration.PartyIntegrationConfiguration;

@FeignClient(
	name = INTEGRATION_NAME,
	url = "${integration.party.base-url}",
	configuration = PartyIntegrationConfiguration.class,
	dismiss404 = true)
@CircuitBreaker(name = INTEGRATION_NAME)
public interface PartyClient {
	@GetMapping(value = "/{municipalityId}/{type}/{legalId}/partyId", produces = MediaType.TEXT_PLAIN_VALUE)

	Optional<String> getPartyId(@PathVariable("legalId") String personNumber,
		@PathVariable("municipalityId") String municipalityId,
		@PathVariable("type") String type);
}
