package org.edinar.abnamrofxtradeclient;

import java.io.IOException;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * This test is disabled by default, because it makes remote requests to the
 * ABN AMRO sandbox environment. Remember to change the credentials in the
 * properties file before running this test.
 */
@Disabled
public class AbnAmroFxTradeClientTest {
    private AbnAmroFxTradeClient client;

    @BeforeEach
    public void setup() throws IOException {
        client = new AbnAmroFxTradeClient(AbnAmroEnvironment.SANDBOX,
                                          new SimpleSecretManager());
    }

    @Test
    public void testGetAccessToken() throws InterruptedException, IOException {
        AbnAmroAccessToken accessToken = client.getAccessToken();
        Assertions.assertNotNull(accessToken);
        Assertions.assertEquals("3599", accessToken.getExpiresIn());
        Assertions.assertEquals("Bearer ey", accessToken.toString().substring(0, 9));
        Assertions.assertEquals(accessToken, client.getAccessToken(), "The current access token has NOT expired, but a new token was fetched.");
        accessToken.setExpiresIn("0"); // Simulate an expired token
        Assertions.assertNotEquals(accessToken, client.getAccessToken(), "The current token expired, but a new token was NOT fetched.");
    }

    @Test
    public void testGetAllowedCurrencyPairs() throws InterruptedException, IOException {
        Set<String> currencyPairs = client.getAllowedCurrencyPairs();
        Assertions.assertTrue(currencyPairs.contains("EURUSD"));
        Assertions.assertFalse(currencyPairs.contains("USDEUR"));
    }

    @Test
    public void testGetSettlementAccountGroups() throws InterruptedException, IOException {
        Set<String> settlementAccountGroups = client.getSettlementAccountGroups();
        Assertions.assertTrue(settlementAccountGroups.contains("Client Account"));
        Assertions.assertTrue(settlementAccountGroups.contains("House Account"));
    }
}
