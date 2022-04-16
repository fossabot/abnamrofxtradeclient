package org.edinar.abnamrofxtradeclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AbnAmroFxTradeClient {
    private AbnAmroAccessToken currentAccessToken;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final AbnAmroEnvironment environment;
    private final SecretManager secretManager;

    public AbnAmroFxTradeClient(AbnAmroEnvironment environment, SecretManager secretManager) {
        this(HttpClient.newHttpClient(), new ObjectMapper(), environment, secretManager);
    }

    public AbnAmroFxTradeClient(HttpClient httpClient, ObjectMapper objectMapper, AbnAmroEnvironment environment, SecretManager secretManager) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.environment = environment;
        this.secretManager = secretManager;
    }

    protected AbnAmroAccessToken getAccessToken() throws InterruptedException, IOException {
        if (currentAccessToken == null) {
            return getNewAccessToken();
        }
        long expiresIn = Long.parseLong(currentAccessToken.getExpiresIn());
        if (System.currentTimeMillis() > currentAccessToken.getStartedAt() + expiresIn) {
            return getNewAccessToken();
        }
        return currentAccessToken;
    }

    protected AbnAmroAccessToken getNewAccessToken() throws InterruptedException, IOException {
        URI uri = URI.create(environment.getAuthenticationBaseUrl() + "/as/token.oauth2");
        String username = secretManager.getUsername();
        String password = secretManager.getPassword();
        String authorization = username + ":" + password;
        String encodedAuthorization = Base64.getEncoder()
                                            .encodeToString(authorization.getBytes(StandardCharsets.UTF_8));
        String requestBody = "grant_type=client_credentials&scope=" + secretManager.getScope();
        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .setHeader("Authorization", "Basic " + encodedAuthorization)
                                         .setHeader("Content-Type", "application/x-www-form-urlencoded")
                                         .setHeader("Accept", "application/json")
                                         .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                                         .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        currentAccessToken = objectMapper.readValue(response.body(), AbnAmroAccessToken.class);
        currentAccessToken.setStartedAt(System.currentTimeMillis());
        return currentAccessToken;
    }
}
