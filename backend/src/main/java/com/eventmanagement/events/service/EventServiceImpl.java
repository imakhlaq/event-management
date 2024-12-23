package com.eventmanagement.events.service;

import com.eventmanagement.events.DTO.EventDTO;
import com.eventmanagement.googlecalendar.GoogleCalendarConfig;
import com.eventmanagement.utils.DateTimeUtils;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EventServiceImpl implements IEventService {

    final private String calendarId = "primary";
    final private GoogleCalendarConfig service;

    @Override
    public List<Event> getAllEvents(OAuth2AuthorizedClient oAuth2User, Integer month, Integer year) throws GeneralSecurityException, IOException {

        // is user provides a month
        if (month != null && month <= 12) {

            // Define a DateTimeFormatter for the output format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

            // Start of the month
            LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);

            // End of the month (last day of the month, 23:59:59)
            LocalDateTime endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59);

            // Convert to Google's DateTime format
            DateTime startTime = new DateTime(DateTimeUtils.getDateTimeInEpochMilli(startOfMonth));
            DateTime endTime = new DateTime(DateTimeUtils.getDateTimeInEpochMilli(endOfMonth));

            // Fetch events for the month
            Events events = this.service.getCalendar(oAuth2User).events().list(calendarId)
                .setTimeMin(startTime)
                .setTimeMax(endTime)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

            return events.getItems();
        }

        Events events = service.getCalendar(oAuth2User)
            .events()
            .list(calendarId)
            .setOrderBy("startTime")
            .setSingleEvents(true)
            .execute();

        return events.getItems();
    }

    @Override
    public Event getEventById(OAuth2AuthorizedClient oAuth2User, String id) throws GeneralSecurityException, IOException {
        Event event = service.getCalendar(oAuth2User)
            .events()
            .get(calendarId, id)
            .execute();
        return event;
    }

    @Override
    public Event createEvent(OAuth2AuthorizedClient oAuth2User, EventDTO data) throws GeneralSecurityException, IOException {

        if (data.getStartTime().equals(null)) data.setStartTime(LocalDateTime.now());
        if (data.getEndTime().equals(null)) data.setEndTime(data.getStartTime().plusDays(1));

        var time = this.calcStartAndEndTime(data.getStartTime(), data.getEndTime());

        Event event = this.getEventById(oAuth2User, data.getId());
        event.setDescription(data.getDescription());
        event.setSummary(data.getSummary());
        event.setLocation(data.getLocation());
        event.setStart(time.get("eventStartTime"));
        event.setEnd(time.get("eventEndTime"));

        var createdEvent = this.service.getCalendar(oAuth2User)
            .events()
            .insert(calendarId, event)
            .execute();
        return createdEvent;
    }

    @Override
    public Event updateEvent(OAuth2AuthorizedClient oAuth2User, EventDTO data) throws GeneralSecurityException, IOException {

        var time = this.calcStartAndEndTime(data.getStartTime(), data.getEndTime());

        Event event = new Event();
        event.setSummary(data.getSummary());
        event.setDescription(data.getDescription());
        event.setLocation(data.getLocation());
        event.setEventType(data.getEventType());
        event.setStart(time.get("eventStartTime"));
        event.setEnd(time.get("eventEndTime"));

        // Update the event in Google Calendar
        this.service.getCalendar(oAuth2User)
            .events()
            .update(calendarId, data.getId(), event)
            .execute();
        return null;
    }

    private Map<String, EventDateTime> calcStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime) {
        var startTimeEpochMilli = DateTimeUtils.getDateTimeInEpochMilli(startTime);
        var eventStartTime = new EventDateTime();
        eventStartTime.setDateTime(new DateTime(startTimeEpochMilli));

        var endTimeEpochMilli = DateTimeUtils.getDateTimeInEpochMilli(endTime);
        var eventEndTime = new EventDateTime();
        eventEndTime.setDateTime(new DateTime(endTimeEpochMilli));

        return Map.of("eventStartTime", eventStartTime, "eventEndTime", eventEndTime);
    }

    @Override
    public Map deleteEvent(OAuth2AuthorizedClient oAuth2User, String id) throws GeneralSecurityException, IOException {

        this.service.getCalendar(oAuth2User).events().delete(calendarId, id).execute();
        var message = "Event with id " + id + " delete";
        return Map.of("Message", message);
    }
}
