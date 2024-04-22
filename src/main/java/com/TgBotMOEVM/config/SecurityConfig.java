package com.TgBotMOEVM.config;

import com.TgBotMOEVM.security.CustomAuthorizationRequestResolver;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private Environment env;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/*", "/error").permitAll()  // Ensure public access to root, all first-level paths, and error pages.
                        .requestMatchers("/oauth2/authorization/etu",
                                "/ltgbot/login/oauth2/code/etu").permitAll()  // Explicitly allow OAuth2 initiation without authentication.
                        .anyRequest().authenticated()  // Require authentication for all other requests.
                )
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint()
                        .authorizationRequestResolver(new CustomAuthorizationRequestResolver(
                                clientRegistrationRepository(), DEFAULT_AUTHORIZATION_REQUEST_BASE_URI
                        ))
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new Http403ForbiddenEntryPoint())  // Handle unauthenticated access to protected endpoints.
                );

        return http.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(clientRegistration());
    }

    private ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("etu")
                .clientId("9bc6c414-2f20-4167-8fbb-513a3fb81acb")
                .clientSecret("hGXWuTL1yNZT1SaJs5QTZRENj9PP25xpDcb0bp90")
                .scope("profile")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/ltgbot/login/oauth2/code/etu")
                .authorizationUri("https://id.etu.ru/authorize")
                .tokenUri("https://id.etu.ru/oauth/token")
                .userInfoUri("https://id.etu.ru/api/userinfo")
                .userNameAttributeName("id")
                .build();
    }
}
