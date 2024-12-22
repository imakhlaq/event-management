package com.eventmanagement.auth.successhandler;

import com.eventmanagement.auth.repository.IUserRepo;
import com.eventmanagement.models.User;
import com.eventmanagement.utils.TokenUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;

@Slf4j
@Component
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {

    final private IUserRepo userRepo;
    final private RestClient restClient;
    final private TokenUtils tokenUtils;

    public OAuthLoginSuccessHandler(IUserRepo userRepo, RestClient restClient, TokenUtils tokenUtils) {
        this.userRepo = userRepo;
        this.restClient = restClient;
        this.tokenUtils = tokenUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //TODO get refresh token and store it in db. Refresh token is only available on first signup only
        var oAuth2Client = (OAuth2AuthenticationToken) authentication;
        var email = (String) oAuth2Client.getPrincipal().getAttributes().get("email");
        var refreshToken = this.tokenUtils.getRefreshToken();
        var username = oAuth2Client.getPrincipal().getName();
        var authid = oAuth2Client.getAuthorizedClientRegistrationId();

        log.info("Got Refresh_Token and stored it in db");
        log.info("Refresh_Token is {} and user name is {}", refreshToken, username);
        //insert token in the refresh_token into db
        var user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setAuthId(authid);
        user.setRefreshToken(refreshToken);
        userRepo.save(user);

        response.sendRedirect("http://localhost:3000/dashboard");
    }
}