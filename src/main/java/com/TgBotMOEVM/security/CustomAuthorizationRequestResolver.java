package com.TgBotMOEVM.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {
    private final ClientRegistrationRepository clientRegistrationRepository;
    private final String registrationId;
    private static final SecureRandom secureRandom = new SecureRandom();

    private String codeVerifier;
    private String codeChallenge;
    private String state;

    public CustomAuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository, String registrationId) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.registrationId = registrationId;

        generateCodeVerifier(); // Генерируем codeVerifier
        generateCodeChallenge(); // Генерируем codeChallenge
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(registrationId);
        if (clientRegistration == null) {
            return null;
        }

        generateCodeVerifier(); // Генерируем codeVerifier
        generateCodeChallenge(); // Генерируем codeChallenge

        Map<String, Object> additionalParameters = new HashMap<>();
        additionalParameters.put("code_challenge", codeChallenge);
        additionalParameters.put("code_challenge_method", "S256");
        additionalParameters.put("code_verifier", codeVerifier);  // Note: This should not be sent to the authorization server

        return OAuth2AuthorizationRequest.authorizationCode()
                .clientId(clientRegistration.getClientId())
                .authorizationUri(clientRegistration.getProviderDetails().getAuthorizationUri())
                .redirectUri(clientRegistration.getRedirectUri())
                .scopes(clientRegistration.getScopes())
                .state(generateState())
                .additionalParameters(additionalParameters)
                .build();
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
        if (clientRegistration == null) {
            return null;
        }

        generateCodeVerifier(); // Генерируем codeVerifier
        generateCodeChallenge(); // Генерируем codeChallenge

        Map<String, Object> additionalParameters = new HashMap<>();
        additionalParameters.put("code_challenge", codeChallenge);
        additionalParameters.put("code_challenge_method", "S256");

        return OAuth2AuthorizationRequest.authorizationCode()
                .clientId(clientRegistration.getClientId())
                .authorizationUri(clientRegistration.getProviderDetails().getAuthorizationUri())
                .redirectUri(clientRegistration.getRedirectUri())
                .scopes(clientRegistration.getScopes())
                .state(generateState())
                .additionalParameters(additionalParameters)
                .build();
    }

    private void generateCodeVerifier() {
        byte[] code = new byte[32];
        secureRandom.nextBytes(code);
        codeVerifier = Base64.getUrlEncoder().withoutPadding().encodeToString(code);
    }

    private void generateCodeChallenge() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(codeVerifier.getBytes());
            codeChallenge = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not found", e);
        }
    }

    private String generateState() {
        state = Base64.getUrlEncoder().encodeToString(new byte[32]);
        return state;
    }
    public String getState() {
        return state;
    }
    public String getCodeVerifier() {
        return codeVerifier;
    }

    public String getCodeChallenge() {
        return codeChallenge;
    }
}
