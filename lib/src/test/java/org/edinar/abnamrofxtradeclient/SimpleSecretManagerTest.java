package org.edinar.abnamrofxtradeclient;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleSecretManagerTest {
    private SecretManager secretManager;

    @BeforeEach
    public void setup() throws IOException {
        secretManager = new SimpleSecretManager();
    }

    @Test
    public void testGetApiKey() {
        String apiKey = secretManager.getApiKey();
        Assertions.assertEquals("<YOUR_API_KEY>", apiKey);
    }

    @Test
    public void testGetUsername() {
        String username = secretManager.getUsername();
        Assertions.assertEquals("FXAPI", username);
    }

    @Test
    public void testGetPassword() {
        String password = secretManager.getPassword();
        Assertions.assertEquals("z5l2KRWEXnmDvBhssPq7", password);
    }

    @Test
    public void testGetScope() {
        String scope = secretManager.getScope();
        Assertions.assertEquals("fxtrade:allowedcurrencypairs:read fxtrade:settlementaccountgroups:read fxtrade:rates:read fxtrade:conversioncalculations:write fxtrade:quotes:read fxtrade:quotes:write fxtrade:orders:read fxtrade:orders:write", scope);
    }
}
