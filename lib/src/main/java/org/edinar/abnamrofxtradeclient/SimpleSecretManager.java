package org.edinar.abnamrofxtradeclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SimpleSecretManager implements SecretManager {
    private static final String FILE = "secret.properties";
    private static final String API_KEY = "apiKey";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String SCOPE = "scope";
    private final Properties properties;

    public SimpleSecretManager() throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream(FILE)) {
            if (inputStream == null) {
                throw new IOException("File not found.");
            }
            properties = new Properties();
            properties.load(inputStream);
        }
    }

    @Override
    public String getApiKey() {
        return properties.getProperty(API_KEY);
    }

    @Override
    public String getUsername() {
        return properties.getProperty(USERNAME);
    }

    @Override
    public String getPassword() {
        return properties.getProperty(PASSWORD);
    }

    @Override
    public String getScope() {
        return properties.getProperty(SCOPE);
    }
}
