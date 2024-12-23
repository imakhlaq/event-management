package com.eventmanagement.events;

import com.eventmanagement.events.DTO.EventDTO;
import com.eventmanagement.events.service.IEventService;
import com.eventmanagement.response.events.WeekSummaryResponse;
import com.google.api.services.calendar.model.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/apiv1/events")
@Tag(name = "Event", description = "the Event Api")
@AllArgsConstructor
@Slf4j
public class EventsController {

    final private IEventService eventService;

    @GetMapping(path = "/get-events", produces = "application/json")
    @Operation(
        summary = "Get all the events from the calendar.",
        description = "Fetches all events from the google calender.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found Events")
    })
    public ResponseEntity<List<Event>> getAllEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @RequestParam(name = "month", required = false) Integer month, @RequestParam(name = "year", required = false) Integer year) throws GeneralSecurityException, IOException, ParseException {

        log.info("Request received for all Events");
        log.info("Optional Params are Year {} month{}", year, month);
        return ResponseEntity.ok(eventService.getAllEvents(oAuth2Client, month, year));
    }

    @Operation(
        summary = "Get a single event by id from the calender.",
        description = "Fetches a single event from google calender by provided id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found a Event")
    })
    @GetMapping("/event-by-id/{id}")
    public ResponseEntity<Event> getEventById(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @PathVariable String id) throws GeneralSecurityException, IOException {

        log.info("Request received for Event by {}", id);
        return ResponseEntity.ok(eventService.getEventById(oAuth2Client, id));
    }

    @PostMapping("/add-events")
    @Operation(
        summary = "Add events to the calender",
        description = "Add events to the calendar")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<Event> addEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @RequestBody EventDTO eventDTO) throws GeneralSecurityException, IOException {

        log.info("Request received for add event");
        return ResponseEntity.ok(this.eventService.createEvent(oAuth2Client, eventDTO));
    }

    @PatchMapping("/update-event")
    @Operation(
        summary = "Fetch all plants",
        description = "fetches all plant entities and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<Event> updateEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @RequestBody EventDTO eventUpdateDTO) throws GeneralSecurityException, IOException {

        log.info("Request received update event");
        return ResponseEntity.ok(this.eventService.updateEvent(oAuth2Client, eventUpdateDTO));
    }

    @DeleteMapping("/delete-event/{id}")
    @Operation(
        summary = "Deletes a calender event",
        description = "Deletes a calender event data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<Map<String, String>> deleteEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @PathVariable String id) throws GeneralSecurityException, IOException {

        log.info("Request received delete event");
        return ResponseEntity.ok(this.eventService.deleteEvent(oAuth2Client, id));
    }

    @GetMapping("/week-summary")
    @Operation(
        summary = "Get Summary for one week",
        description = "Get Summary for a week from Google calender")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<WeekSummaryResponse> thisWeekSummary(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client) throws GeneralSecurityException, IOException {

        log.info("Request received for this week summary");
        return ResponseEntity.ok(eventService.thisWeekSummary(oAuth2Client));
    }
}