package com.eventmanagement.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public TokenUtils(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    public String getAccessToken() {
        OAuth2AuthorizedClient client = getClient();
        return client.getAccessToken().getTokenValue();
    }

    public String getRefreshToken() {
        OAuth2AuthorizedClient client = getClient();
        return client.getRefreshToken().getTokenValue();
    }

    public OAuth2AuthorizedClient getClient() {
        OAuth2AuthenticationToken oauthToken = getOAuthToken();
        return authorizedClientService.loadAuthorizedClient(
            oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
    }

    private OAuth2AuthenticationToken getOAuthToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
            return (OAuth2AuthenticationToken) authentication;
        }
        throw new SecurityException("Authentication is not OAuth2AuthenticationToken");
    }
}