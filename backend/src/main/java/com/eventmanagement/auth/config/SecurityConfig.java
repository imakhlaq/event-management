package com.eventmanagement.auth.config;

import com.eventmanagement.auth.repository.IUserRepo;
import com.eventmanagement.auth.successhandler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final IUserRepo userRepo;
    private final RestClient restClient;
    private final CustomOAuth2UserService oAuth2UserService;

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
                req.requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/**", "/oauth2/callback").
                    permitAll().anyRequest().authenticated())
            .oauth2Login(oauth2 ->
                oauth2
                    .userInfoEndpoint(userInfo -> userInfo
                        .userService(oAuth2UserService)
                    )
                    .successHandler(new OAuthLoginSuccessHandler(this.userRepo, this.restClient))
            ).exceptionHandling(exception ->
                exception
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                    .accessDeniedHandler(new CustomAccessDeniedHandler())
            );

        return http.build();
    }
}
