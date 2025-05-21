package se.sundsvall.citizen.integration.party.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.party")
public record PartyIntegrationProperties(int connectTimeout, int readTimeout) {
}
