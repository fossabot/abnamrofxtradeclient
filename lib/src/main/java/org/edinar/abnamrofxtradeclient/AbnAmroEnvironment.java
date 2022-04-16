package org.edinar.abnamrofxtradeclient;

public enum AbnAmroEnvironment {
    PRODUCTION("https://auth.connect.abnamro.com:8443", "https://api.abnamro.com"),
    SANDBOX("https://auth-sandbox.abnamro.com", "https://api-sandbox.abnamro.com"),
    ;

    private final String authenticationBaseUrl;
    private final String apiBaseUrl;

    AbnAmroEnvironment(String authenticationBaseUrl, String apiBaseUrl) {
        this.authenticationBaseUrl = authenticationBaseUrl;
        this.apiBaseUrl = apiBaseUrl;
    }

    public String getAuthenticationBaseUrl() {
        return authenticationBaseUrl;
    }

    public String getApiBaseUrl() {
        return apiBaseUrl;
    }
}
