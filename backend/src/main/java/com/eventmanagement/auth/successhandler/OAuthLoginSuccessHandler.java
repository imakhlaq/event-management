package com.eventmanagement.auth.successhandler;

import com.eventmanagement.auth.repository.IUserRepo;
import com.eventmanagement.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final IUserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //TODO get refresh token and store it in db. Refresh token is only available on first signup only
        var oAuth2Client = (OAuth2AuthorizedClient) authentication;
        var refreshToken = oAuth2Client.getRefreshToken().getTokenValue();
        var username = oAuth2Client.getPrincipalName();
        log.debug("Got Refresh_Token and stored it in db");
        log.debug("Refresh_Token is {} and user name is {}", refreshToken, username);
        //insert token in the refresh_token into db
        var user = new User();
        user.setUsername(username);
        user.setAuthId("");
        user.setRefreshToken(refreshToken);
        userRepo.save(user);
    }
}