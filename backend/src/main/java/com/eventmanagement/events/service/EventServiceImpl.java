package com.eventmanagement.events.service;

import com.eventmanagement.googlecalendar.GoogleCalendarConfig;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService {

    final private String calendarId = "primary";
    final private GoogleCalendarConfig service;

    @Override
    public Object getAllEvents(OAuth2AuthorizedClient oAuth2User) throws GeneralSecurityException, IOException {

        Events events = service.getCalendar(oAuth2User)
            .events()
            .list(calendarId)
            .setOrderBy("startTime")
            .setSingleEvents(true)
            .execute();

        return events;
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
