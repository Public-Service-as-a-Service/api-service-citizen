package se.sundsvall.citizen.integration.party;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PartyIntegration {
	private static final Logger LOG = LoggerFactory.getLogger(PartyIntegration.class);
	private final PartyClient client;

	public PartyIntegration(final PartyClient client) {
		this.client = client;
	}

	public String getPartyId(final String personNumber, String municipalityId, String type) {
		try {
			return client.getPartyId(personNumber, municipalityId, type);
		} catch (final Exception e) {
			LOG.info("Unable to get party id", e);
			return null;
		}
	}
}
