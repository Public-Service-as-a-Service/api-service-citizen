package se.sundsvall.citizen.integration.party;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se.sundsvall.citizen.integration.party.configuration.PartyIntegrationConfiguration;

import static se.sundsvall.citizen.integration.party.configuration.PartyIntegrationConfiguration.CLIENT_ID;


@FeignClient(
        name = CLIENT_ID,
        url = "${integration.party.base-url}",
        configuration = PartyIntegrationConfiguration.class)
@CircuitBreaker(name = CLIENT_ID)
public interface PartyClient {
    @GetMapping("/{municipalityId}/{type}/{legalId}/partyId")
    String getPartyId(@PathVariable String legalId, @PathVariable String municipalityId, @PathVariable String type);
}
