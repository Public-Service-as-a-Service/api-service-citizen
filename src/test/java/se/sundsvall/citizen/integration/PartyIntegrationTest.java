package se.sundsvall.citizen.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import feign.FeignException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.citizen.integration.party.PartyClient;
import se.sundsvall.citizen.integration.party.PartyIntegration;

@ExtendWith(MockitoExtension.class)
public class PartyIntegrationTest {
	private static final String TYPE = "PRIVATE";
	@Mock
	private PartyClient mockPartyClient;

	@InjectMocks
	private PartyIntegration partyIntegration;

	@Test
	void getPartyId() {
		final var personalNumber = "123456789";
		final var municipalityId = "2281";
		final var type = "PRIVATE";
		final var partyId = UUID.randomUUID().toString();

		when(mockPartyClient.getPartyId(personalNumber, municipalityId, type)).thenReturn(partyId);
		// act
		final var result = partyIntegration.getPartyId(personalNumber, municipalityId, type);
		assertThat(result).isEqualTo(partyId);
		verify(mockPartyClient).getPartyId(personalNumber, municipalityId, type);
		verifyNoMoreInteractions(mockPartyClient);
	}

	@Test
	void getPartyIdWithException() {
		final var personalNumber = "123456789";
		final var municipalityId = "2281";
		final var type = "PRIVATE";

		doThrow(FeignException.FeignClientException.class)
			.when(mockPartyClient).getPartyId(personalNumber, municipalityId, type);

		final var result = partyIntegration.getPartyId(personalNumber, municipalityId, type);
		assertThat(result).isNull();
		verify(mockPartyClient).getPartyId(personalNumber, municipalityId, type);
		verifyNoMoreInteractions(mockPartyClient);
	}
}
