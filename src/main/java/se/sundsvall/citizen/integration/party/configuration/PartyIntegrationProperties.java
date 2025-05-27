package se.sundsvall.citizen.integration.party.configuration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Primary;
import se.sundsvall.citizen.integration.Oauth2;

@Primary
@ConfigurationProperties(prefix = "integration.party")
public record PartyIntegrationProperties(
	@NotBlank String baseUrl,
	@DefaultValue("PT10S") Duration connectTimeout,
	@DefaultValue("PT30S") Duration readTimeout,
	@Valid @NotNull Oauth2 oauth2) {
}
