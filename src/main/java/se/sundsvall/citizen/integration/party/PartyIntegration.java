package se.sundsvall.citizen.integration.party;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PartyIntegration {
    private static final Logger LOG = LoggerFactory.getLogger(PartyIntegration.class);
    private final PartyClient client;
    private final ObjectMapper objectMapper;

    public PartyIntegration(final PartyClient client, final ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public String getPartyId(final String legalId, String municipalityId, String type) {
        try {
            String answer = client.getPartyId(legalId, municipalityId, type);
            return objectMapper.readValue(answer, String.class);
        } catch (final Exception e) {
            LOG.info("Unable to get this", e);
            return null;
        }
    }
}
