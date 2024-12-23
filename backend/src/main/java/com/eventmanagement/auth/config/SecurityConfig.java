package com.eventmanagement.auth.config;

import com.eventmanagement.repository.IUserRepo;
import com.eventmanagement.auth.successhandler.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;
import org.springframework.web.cors.CorsConfiguration;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
@Slf4j
public class SecurityConfig {

    final private IUserRepo userRepo;
    final private RestClient restClient;
    final private CustomOAuth2UserService oAuth2UserService;
    final private ObjectMapper objectMapper = new ObjectMapper();
    @Value("${login-url}")
    private String logInURl;
    @Value("${redirect-url-after-successful-login}")
    private String redirectUrlAfterSuccessfulLogin;

    public SecurityConfig(IUserRepo userRepo, RestClient restClient, CustomOAuth2UserService oAuth2UserService) {
        this.userRepo = userRepo;
        this.restClient = restClient;
        this.oAuth2UserService = oAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
            .cors(corsConfig ->
                corsConfig.configurationSource(req -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://127.0.0.1:3000", "http://localhost:3000", "https://accounts.google.com", "https://accounts.google.com/", "https://accounts.google.com/**"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req ->
                req.requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/**", "/oauth2/**").
                    permitAll().anyRequest().authenticated())
            .exceptionHandling(exception ->
                exception
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint(this.logInURl))
                    .accessDeniedHandler(new CustomAccessDeniedHandler(this.logInURl))
            )
            .oauth2Login(oauth2 ->
                oauth2
                    .userInfoEndpoint(userInfo -> userInfo
                        .userService(oAuth2UserService)
                    )
                    .successHandler(new OAuthLoginSuccessHandler(this.userRepo, this.redirectUrlAfterSuccessfulLogin))
                    .failureHandler(new CustomOauthFailHandler())
            );
        return http.build();
    }
}
