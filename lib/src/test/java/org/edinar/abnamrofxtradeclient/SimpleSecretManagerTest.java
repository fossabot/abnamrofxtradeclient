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
        Assertions.assertNotNull(apiKey);
    }

    @Test
    public void testGetUsername() {
        String username = secretManager.getUsername();
        Assertions.assertNotNull(username);
    }

    @Test
    public void testGetPassword() {
        String password = secretManager.getPassword();
        Assertions.assertNotNull(password);
    }

    @Test
    public void testGetScope() {
        String scope = secretManager.getScope();
        Assertions.assertNotNull(scope);
    }
}
