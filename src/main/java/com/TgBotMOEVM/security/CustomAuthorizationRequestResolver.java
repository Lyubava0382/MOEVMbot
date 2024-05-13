package com.TgBotMOEVM.security;

import com.TgBotMOEVM.config.Storage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

public class CustomAuthorizationRequestResolver implements OAuth2AuthorizationRequestResolver {

    private final OAuth2AuthorizationRequestResolver defaultResolver;

    private final StringKeyGenerator secureKeyGenerator =
            new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 96);

    public CustomAuthorizationRequestResolver(ClientRegistrationRepository repo, String authorizationRequestBaseUri) {
        this.defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(repo, authorizationRequestBaseUri);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest servletRequest) {
        OAuth2AuthorizationRequest req = defaultResolver.resolve(servletRequest);
        return customizeAuthorizationRequest(req, servletRequest);
    }

    @Override
    public OAuth2AuthorizationRequest resolve(HttpServletRequest servletRequest, String clientRegistrationId) {
        OAuth2AuthorizationRequest req = defaultResolver.resolve(servletRequest, clientRegistrationId);
        return customizeAuthorizationRequest(req, servletRequest);
    }

    private OAuth2AuthorizationRequest customizeAuthorizationRequest(OAuth2AuthorizationRequest req, HttpServletRequest servletRequest) {
        if (req == null) {
            return null;
        }

        String codeVerifier = secureKeyGenerator.generateKey();
        String codeChallenge;
        try {
            codeChallenge = createHash(codeVerifier);
        } catch (NoSuchAlgorithmException e) {
            codeChallenge = codeVerifier;
        }
        Storage storage = Storage.getInstance();
        storage.setState(req.getState());
        storage.setCodeVerifier(codeVerifier);
        return OAuth2AuthorizationRequest.from(req)
                .clientId("SECRET")
                .redirectUri("http://localhost:8080/ltgbot/login/oauth2/code/etu")
                .scope("profile")
                .state(req.getState())
                .authorizationUri("https://id.etu.ru/authorize")
                .additionalParameters(Map.of(
                        "code_challenge", codeChallenge,
                        "code_challenge_method", "S256"
                ))
                .build();
    }

    private static String createHash(String value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(value.getBytes(StandardCharsets.US_ASCII));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }
}
