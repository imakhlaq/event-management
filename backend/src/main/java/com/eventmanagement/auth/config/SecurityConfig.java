package com.eventmanagement.auth.config;

import com.eventmanagement.auth.successhandler.OAuthLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration

public class SecurityConfig {

    final String redirectUrl;
    final OAuthLoginSuccessHandler oAuthLoginSuccessHandler;

    public SecurityConfig(OAuthLoginSuccessHandler oAuthLoginSuccessHandler, @Value("${redirect_url}") String redirectUrl) {
        this.redirectUrl = redirectUrl;
        this.oAuthLoginSuccessHandler = oAuthLoginSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
            .cors(corsConfig ->
                corsConfig.configurationSource(req -> {
                    var corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
                    corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://127.0.0.1:3000"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
                    corsConfiguration.setAllowCredentials(true);
                    return corsConfiguration;
                }))
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req ->
                req.anyRequest().authenticated())
            .oauth2Login(oauth2 ->
                oauth2.successHandler(oAuthLoginSuccessHandler)
                    .defaultSuccessUrl(redirectUrl, true));
        return http.build();
    }
}
