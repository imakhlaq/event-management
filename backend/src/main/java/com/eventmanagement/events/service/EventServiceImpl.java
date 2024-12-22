package com.eventmanagement.events.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements IEventService {

    @Override
    public Object getAllEvents(OAuth2AuthorizedClient oAuth2User) {

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
