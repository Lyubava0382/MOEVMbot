package com.TgBotMOEVM.controller;

import com.TgBotMOEVM.config.Storage;
import com.TgBotMOEVM.model.AuthResponse;
import com.TgBotMOEVM.model.ProfileResponse;
import com.TgBotMOEVM.service.impl.ProfileServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;
    private final ProfileServiceImpl profileService;

    @GetMapping("/ltgbot/login/oauth2/code/etu")
    public void exchangeAuthorizationCodeForToken(
            @RequestParam("code") String code,
            @RequestParam("state") String state) {
        //System.out.println(code);
        Storage storage = Storage.getInstance();
        String expectedState = storage.getState();
        if (state.equals(expectedState)) {
            HttpClient client = HttpClient.newHttpClient();
            String url = "https://id.etu.ru/oauth/token";
            Map<Object, Object> data = new HashMap<>();
            data.put("grant_type", "authorization_code");
            data.put("code", code);
            data.put("redirect_uri", "http://localhost:8080/ltgbot/login/oauth2/code/etu");
            data.put("client_id", "9bc6c414-2f20-4167-8fbb-513a3fb81acb");
            data.put("client_secret", "hGXWuTL1yNZT1SaJs5QTZRENj9PP25xpDcb0bp90");
            data.put("code_verifier", storage.getCodeVerifier());

            String form = data.keySet().stream()
                    .map(key -> URLEncoder.encode(key.toString(), StandardCharsets.UTF_8) + "=" +
                            URLEncoder.encode(data.get(key).toString(), StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(form))
                    .build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("Response status code: " + response.statusCode());
                System.out.println("Response body: " + response.body());

                ObjectMapper objectMapper = new ObjectMapper();
                AuthResponse initialAuthResponse = objectMapper.readValue(response.body(), AuthResponse.class);

                AuthResponse authResponse = modelMapper.map(initialAuthResponse, AuthResponse.class);
                if (authResponse.getStatus() != null) {
                    throw new RuntimeException("Error: " + authResponse.getMessage());
                }
                else {
                    getProfile(authResponse.getAccess_token());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else System.out.println("State error");
    }



    private ProfileResponse getProfile(String accessToken) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        System.out.println("Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = restTemplate.exchange("https://id.etu.ru/api/profile", HttpMethod.GET, entity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ProfileResponse initialAuthResponse = objectMapper.readValue(response.getBody(), ProfileResponse.class);
        // Используем ModelMapper для дополнительного маппинга, если это нужно
        ProfileResponse profileResponse = modelMapper.map(initialAuthResponse, ProfileResponse.class);
        Storage storage = Storage.getInstance();
        storage.setProfileResponse(profileResponse);

        profileService.create(profileResponse.getData());

        return profileResponse;
    }
    @GetMapping("/success")
    public String handleSuccess() {
        System.out.println("success");
        return "success";
    }
    @GetMapping("/error")
    public String handleError() {
        System.out.println("ERROR");
        return "error";
    }
    }
