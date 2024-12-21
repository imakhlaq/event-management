package com.eventmanagement.events.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface IEventService {
    Object getAllEvents(OAuth2AuthorizedClient oAuth2User) throws GeneralSecurityException, IOException;

    void createEvent(OAuth2AuthorizedClient oAuth2User, Object data);

    void updateEvent(OAuth2AuthorizedClient oAuth2User, String id, Object data);

    void deleteEvent(OAuth2AuthorizedClient oAuth2User, String id);
}