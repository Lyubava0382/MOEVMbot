package com.TgBotMOEVM.security;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("")
public class AuthController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    // Метод для редиректа на страницу авторизации
    @GetMapping("/ltgbot/login/oauth2/auth/etu")
    public String redirectToExternalAuth() {
        return "redirect:/oauth2/authorization/etu"; // 'etu' это имя ClientRegistration
    }

    // Обработка редиректа с сервера авторизации
    @GetMapping("/ltgbot/login/oauth2/code/etu")
    public String handleAuthorizationCode(OAuth2AuthenticationToken authentication, Model model) {
        OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName());

        if (client == null) {
            model.addAttribute("error", "Проблемы при получении информации об OAuth клиенте");
            return "error";
        }

        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        model.addAttribute("username", oidcUser.getName());
        model.addAttribute("idToken", oidcUser.getIdToken().getTokenValue());
        model.addAttribute("accessToken", client.getAccessToken().getTokenValue());

        return "user-profile"; // Возвращает имя представления страницы профиля пользователя
    }

    // Дополнительный обработчик ошибок
    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("error", "Неизвестная ошибка во время OAuth авторизации");
        return "error";
    }
}
