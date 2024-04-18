package com.TgBotMOEVM.config;

import com.TgBotMOEVM.security.CustomAuthorizationRequestResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(c -> c
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/ltgbot/login/oauth2/code/etu", "/success", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .authorizationEndpoint(authorizationEndpointConfig -> {
                            OAuth2AuthorizationRequestResolver resolver =
                                    customAuthorizationRequestResolver(clientRegistrationRepository());
                            authorizationEndpointConfig.authorizationRequestResolver(resolver);
                        })
                        .defaultSuccessUrl("/success", true)
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
    @Bean
    public CustomAuthorizationRequestResolver customAuthorizationRequestResolver(ClientRegistrationRepository clientRegistrationRepository) {
        CustomAuthorizationRequestResolver resolver =  new CustomAuthorizationRequestResolver(clientRegistrationRepository, "etu");
        return resolver;
    }
}
