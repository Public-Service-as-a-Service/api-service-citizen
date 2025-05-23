package se.sundsvall.citizen.integration.party;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se.sundsvall.citizen.integration.party.configuration.PartyIntegrationConfiguration;

import static se.sundsvall.citizen.integration.party.configuration.PartyIntegrationConfiguration.INTEGRATION_NAME;

@FeignClient(
	name = INTEGRATION_NAME,
	url = "${integration.party.base-url}",
	configuration = PartyIntegrationConfiguration.class)
@CircuitBreaker(name = INTEGRATION_NAME)
public interface PartyClient {
	@GetMapping(value = "/{municipalityId}/{type}/{legalId}/partyId", produces = MediaType.TEXT_PLAIN_VALUE)

	String getPartyId(@PathVariable("legalId") String personNumber,
		@PathVariable("municipalityId") String municipalityId,
		@PathVariable("type") String type);
}
