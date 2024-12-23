package com.eventmanagement.auth.successhandler;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // Fetch user info from the provider
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Custom logic to map provider user info to your app's user model
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Example: Return a custom user representation
        return new DefaultOAuth2User(
            Collections.singleton(() -> "ROLE_USER"), // Roles/authorities
            oAuth2User.getAttributes(), // User attributes from provider
            name // Identifier attribute key
        );
    }
}