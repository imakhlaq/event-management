package com.eventmanagement.auth.successhandler;

import com.eventmanagement.repository.IUserRepo;
import com.eventmanagement.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {

    final private IUserRepo userRepo;
    @Value("${redirect-url-after-successful-login}")
    private String redirectUrlAfterSuccessfulLogin;

    public OAuthLoginSuccessHandler(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //adding user to the db
        var oAuth2Client = (OAuth2AuthenticationToken) authentication;
        var email = (String) oAuth2Client.getPrincipal().getAttributes().get("email");
        var username = oAuth2Client.getPrincipal().getName();
        var authid = oAuth2Client.getAuthorizedClientRegistrationId();

        var userInDB = this.userRepo.findUserByUsername(username);
        if (userInDB.isPresent()) response.sendRedirect(redirectUrlAfterSuccessfulLogin);

        log.info("Login SuccessFull username {}", username);
        //insert token in the refresh_token into db
        var user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setAuthId(authid);
        userRepo.save(user);
        log.info("Added {} to db", user.getUsername());

        response.sendRedirect(redirectUrlAfterSuccessfulLogin);
    }
}