package com.eventmanagement.events.service;

import com.eventmanagement.events.DTO.EventDTO;
import com.eventmanagement.response.events.WeekSummaryResponse;
import com.google.api.services.calendar.model.Event;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface IEventService {
    List<Event> getAllEvents(OAuth2AuthorizedClient oAuth2User, Integer month, Integer Year) throws GeneralSecurityException, IOException, ParseException;

    Event getEventById(OAuth2AuthorizedClient oAuth2User, String id) throws GeneralSecurityException, IOException;

    Event createEvent(OAuth2AuthorizedClient oAuth2User, EventDTO data) throws GeneralSecurityException, IOException;

    Event updateEvent(OAuth2AuthorizedClient oAuth2User, EventDTO data) throws GeneralSecurityException, IOException;

    Map<String, String> deleteEvent(OAuth2AuthorizedClient oAuth2User, String id) throws GeneralSecurityException, IOException;

    WeekSummaryResponse thisWeekSummary(OAuth2AuthorizedClient oAuth2User) throws GeneralSecurityException, IOException;
}