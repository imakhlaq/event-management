package com.eventmanagement.auth.config;

import com.eventmanagement.auth.repository.IUserRepo;
import com.eventmanagement.auth.successhandler.OAuthLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestClient;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final OAuth2AuthorizationRequestResolver defaultAuthorizationRequestResolver;
    private final IUserRepo userRepo;
    private final RestClient restClient;

    public SecurityConfig(OAuth2AuthorizationRequestResolver defaultAuthorizationRequestResolver1, IUserRepo userRepo, RestClient restClient) {

        this.defaultAuthorizationRequestResolver = defaultAuthorizationRequestResolver1;
        this.userRepo = userRepo;
        this.restClient = restClient;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
            .cors(corsConfig ->
                corsConfig.configurationSource(req -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://127.0.0.1:3000", "http://localhost:3000", "https://accounts.google.com"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req ->
                req.requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/**").permitAll().anyRequest().authenticated())
            .oauth2Login(oauth2 ->
                oauth2
                    .successHandler(new OAuthLoginSuccessHandler(this.userRepo, this.restClient))
                    .authorizationEndpoint(auth ->
                        auth.authorizationRequestResolver(
                            this.defaultAuthorizationRequestResolver))
            );
        return http.build();
    }
}
