package com.TgBotMOEVM.controller;

import org.springframework.http.*;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {

    //@GetMapping("/ltgbot/login/oauth2/code/etu")
    //public ResponseEntity<String> handleAuthorizationCode(@RequestParam("code") String code, @RequestParam("state") String state) {

    @PostMapping("/ltgbot/login/oauth2/code/etu")
    public ResponseEntity<String> exchangeAuthorizationCodeForToken(
            @RequestParam("code") String code,
            @RequestParam("state") String state) {

        // Сверяем параметр state со значением, которое было сохранено до перенаправления
        String expectedState = "тут должно быть сохраненное значение state";
        if (!state.equals(expectedState)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверное значение параметра state");
        }

        // Получите параметры клиента из вашей конфигурации
        String clientId = "bc6c414-2f20-4167-8fbb-513a3fb81acb";
        String redirectUri = "http://localhost:8080/ltgbot/login/oauth2/code/etu";
        String tokenEndpoint = "https://id.etu.ru/oauth/token";

        // Создайте объект RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Создайте параметры тела запроса для обмена кода авторизации на токен доступа
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("client_id", clientId);
        requestBody.add("redirect_uri", redirectUri);
        requestBody.add("code", code);

        // Создайте объект запроса
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Выполните POST запрос к конечной точке токенов
        ResponseEntity<OAuth2AccessToken> responseEntity = restTemplate.postForEntity(tokenEndpoint, requestEntity, OAuth2AccessToken.class);

        // Обработайте ответ и верните результат
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            OAuth2AccessToken accessToken = responseEntity.getBody();
            // Верните токен доступа клиенту или выполните другие действия с ним
            return ResponseEntity.ok("Токен доступа успешно получен: " + accessToken.getTokenValue());
        } else {
            // Обработайте ошибку, если запрос завершился неудачно
            return ResponseEntity.status(responseEntity.getStatusCode()).body("Ошибка при получении токена доступа");
        }
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
