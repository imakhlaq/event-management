package com.eventmanagement.events.service;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.calendar.Calendar;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class EventServiceImpl implements IEventService {

    @Override
    public Object getAllEvents(OAuth2AuthorizedClient oAuth2User) throws GeneralSecurityException, IOException {

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod());
        credential.setAccessToken(oAuth2User.getAccessToken().getTokenValue());

        //TODO get refresh token from db
        credential.setRefreshToken("refreshtoken");

        return null;
    }

    @Override
    public void createEvent(OAuth2AuthorizedClient oAuth2User, Object data) {

    }

    @Override
    public void updateEvent(OAuth2AuthorizedClient oAuth2User, String id, Object data) {

    }

    @Override
    public void deleteEvent(OAuth2AuthorizedClient oAuth2User, String id) {

    }
}
