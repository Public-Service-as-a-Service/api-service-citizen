package se.sundsvall.citizen.integration.party.configuration;

import org.springframework.cloud.openfeign.FeignBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import se.sundsvall.dept44.configuration.feign.FeignMultiCustomizer;
import se.sundsvall.dept44.configuration.feign.decoder.ProblemErrorDecoder;

public class PartyIntegrationConfiguration {
    public static final String CLIENT_ID = "citizen";

    @Bean
    FeignBuilderCustomizer feignBuilderCustomizer(final PartyIntegrationProperties citizenIntegrationProperties) {
        return FeignMultiCustomizer.create()
                .withErrorDecoder(new ProblemErrorDecoder(CLIENT_ID))
                .withRequestTimeoutsInSeconds(citizenIntegrationProperties.connectTimeout(), citizenIntegrationProperties.readTimeout())
                .composeCustomizersToOne();
    }
}
