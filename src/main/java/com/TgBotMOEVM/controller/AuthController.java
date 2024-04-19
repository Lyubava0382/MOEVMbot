package com.TgBotMOEVM.controller;

import com.TgBotMOEVM.DTO.AuthResponseDTO;
import com.TgBotMOEVM.security.CustomAuthorizationRequestResolver;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {



    //@GetMapping("/ltgbot/login/oauth2/code/etu")
    //public ResponseEntity<String> handleAuthorizationCode(@RequestParam("code") String code, @RequestParam("state") String state) {



    @GetMapping("/ltgbot/login/oauth2/code/etu")
    public void exchangeAuthorizationCodeForToken(
            @RequestParam("code") String code,
            @RequestParam("state") String state) {
        System.out.println("TUT");
//        // Сверяем параметр state со значением, которое было сохранено до перенаправления
//        String expectedState = customAuthorizationRequestResolver.getState();
//        if (state.equals(expectedState)) {
//            // Получите параметры клиента из вашей конфигурации
//            String clientId = "bc6c414-2f20-4167-8fbb-513a3fb81acb";
//            String redirectUri = "http://localhost:8080/ltgbot/login/oauth2/code/etu";
//            String tokenEndpoint = "https://id.etu.ru/oauth/token";
//            RestTemplate restTemplate = new RestTemplate();
//            // Параметры тела запроса
//            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
//            requestBody.add("grant_type", "authorization_code");
//            requestBody.add("client_id", clientId);
//            requestBody.add("redirect_uri", redirectUri);
//            requestBody.add("code_verifier", customAuthorizationRequestResolver.getCodeVerifier());
//            requestBody.add("code", code);
//            requestBody.add("client_secret","hGXWuTL1yNZT1SaJs5QTZRENj9PP25xpDcb0bp90");
//            // Объект запроса
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            // Создание объекта HttpEntity с параметрами запроса и заголовками
//            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
//            // Отправка POST-запроса tokenEndpoint
//            ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenEndpoint, requestEntity, String.class);
//            System.out.println("Response: " + responseEntity.getBody());
//        }
//        else System.out.println("State error");
    }

//    @PostMapping("/ltgbot/login/oauth2/code/etu")
//    public ResponseEntity<HttpStatus> exchangeAuthorizationCodeForToken(@RequestBody @Valid AuthResponseDTO responseDTO) {
//        System.out.println("YES");
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

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
