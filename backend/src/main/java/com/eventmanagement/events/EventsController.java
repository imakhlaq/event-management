package com.eventmanagement.events;

import com.eventmanagement.events.DTO.EventDTO;
import com.eventmanagement.events.service.IEventService;
import com.google.api.services.calendar.model.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

@RestController
@RequestMapping(path = "/apiv1/events")
@Tag(name = "Event", description = "the Event Api")
@AllArgsConstructor
@Slf4j
public class EventsController {

    final private IEventService eventService;

    @GetMapping(path = "/get-events", produces = "application/json")
    @Operation(
        summary = "Get all the events from the calendar",
        description = "fetches all plant entities and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the book",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = Event.class))}),
    })
    public ResponseEntity<?> getAllEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @RequestParam(name = "month", required = false) Integer month, @RequestParam(name = "year", required = false) Integer year) throws GeneralSecurityException, IOException, ParseException {

        log.info("Request received for all events");
        return ResponseEntity.ok(eventService.getAllEvents(oAuth2Client, month, year));
    }

    @PostMapping("/add-events")
    @Operation(
        summary = "Fetch all plants",
        description = "fetches all plant entities and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<?> addEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @RequestBody EventDTO eventDTO) throws GeneralSecurityException, IOException {

        return ResponseEntity.ok(this.eventService.createEvent(oAuth2Client, eventDTO));
    }

    @PatchMapping("/update-event")
    @Operation(
        summary = "Fetch all plants",
        description = "fetches all plant entities and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<?> updateEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @RequestBody EventDTO eventUpdateDTO) throws GeneralSecurityException, IOException {

        return ResponseEntity.ok(this.eventService.updateEvent(oAuth2Client, eventUpdateDTO));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
        summary = "Fetch all plants",
        description = "fetches all plant entities and their data from data source")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<?> deleteEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client, @PathVariable String id) throws GeneralSecurityException, IOException {

        return ResponseEntity.ok(this.eventService.deleteEvent(oAuth2Client, id));
    }
}