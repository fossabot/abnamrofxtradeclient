package org.edinar.abnamrofxtradeclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Set;
import org.edinar.abnamrofxtradeclient.entities.ConversionCalculationRequest;
import org.edinar.abnamrofxtradeclient.entities.ConversionCalculationResponse;
import org.edinar.abnamrofxtradeclient.entities.IndicativeRate;

public class AbnAmroFxTradeClient {
    private AbnAmroAccessToken currentAccessToken;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final AbnAmroEnvironment environment;
    private final SecretManager secretManager;

    public AbnAmroFxTradeClient(AbnAmroEnvironment environment, SecretManager secretManager) {
        this(HttpClient.newHttpClient(), new AbnAmroObjectMapper(), environment, secretManager);
    }

    public AbnAmroFxTradeClient(HttpClient httpClient, ObjectMapper objectMapper, AbnAmroEnvironment environment, SecretManager secretManager) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.environment = environment;
        this.secretManager = secretManager;
    }

    public Set<String> getAllowedCurrencyPairs() throws InterruptedException, IOException {
        URI uri = URI.create(environment.getApiBaseUrl() + "/v1/fxtrade/allowedcurrencypairs");
        return doGet(new TypeReference<>(){}, uri);
    }

    public Set<String> getAllowedCurrencyPairsBySettlementAccountGroup(String settlementAccountGroup) throws InterruptedException, IOException {
        URI uri = UriBuilder.fromUri(URI.create(environment.getApiBaseUrl() + "/v1/fxtrade/allowedcurrencypairs"))
                            .queryParam("settlementAccountGroup", settlementAccountGroup)
                            .build();
        return doGet(new TypeReference<>(){}, uri);
    }

    public Set<String> getSettlementAccountGroups() throws InterruptedException, IOException {
        URI uri = URI.create(environment.getApiBaseUrl() + "/v1/fxtrade/settlementaccountgroups");
        return doGet(new TypeReference<>(){}, uri);
    }

    public Set<IndicativeRate> getIndicativeRates(Set<String> currencyPairs, String fxRateTenor) throws InterruptedException, IOException {
        URI uri = UriBuilder.fromUri(URI.create(environment.getApiBaseUrl() + "/v1/fxtrade/rates"))
                            .queryParam("currencyPairs", String.join(",", currencyPairs))
                            .queryParam("fxRateTenor", fxRateTenor)
                            .build();
        return doGet(new TypeReference<>(){}, uri);
    }

    public ConversionCalculationResponse performConversionCalculations(Set<ConversionCalculationRequest> conversionCalculationRequests) throws InterruptedException, IOException {
        URI uri = URI.create(environment.getApiBaseUrl() + "/v1/fxtrade/conversioncalculations");
        String data = objectMapper.writeValueAsString(conversionCalculationRequests);
        return doPost(new TypeReference<>(){}, uri, data);
    }

    private <T> T doGet(TypeReference<T> typeReference, URI uri) throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .setHeader("Authorization", getAccessToken().toString())
                                         .setHeader("Accept", "application/json")
                                         .setHeader("API-Key", secretManager.getApiKey())
                                         .GET()
                                         .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), typeReference);
    }

    private <T> T doPost(TypeReference<T> typeReference, URI uri, String data) throws InterruptedException, IOException {
        HttpRequest request = HttpRequest.newBuilder(uri)
                                         .setHeader("Authorization", getAccessToken().toString())
                                         .setHeader("Accept", "application/json")
                                         .setHeader("Content-Type", "application/json")
                                         .setHeader("API-Key", secretManager.getApiKey())
                                         .POST(HttpRequest.BodyPublishers.ofString(data))
                                         .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), typeReference);
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
