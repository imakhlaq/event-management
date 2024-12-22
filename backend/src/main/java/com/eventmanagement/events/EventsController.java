package com.eventmanagement.events;

import com.eventmanagement.events.service.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;

@RestController("/apiv1/events")
//@Api(tags = "Google Calendar Integration Controller")
@AllArgsConstructor
public class EventsController {

    final private IEventService eventService;

    @GetMapping(path = "/get-events", produces = "application/json")
    // @ApiOperation("Gat all the events from the calendar")
    public ResponseEntity<?> getAllEvents(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient oAuth2Client) throws GeneralSecurityException, IOException {

        return ResponseEntity.ok(eventService.getAllEvents(oAuth2Client));
    }

    @PostMapping("/add-events")
    public ResponseEntity<?> addEvents() {

        return null;
    }

    @PatchMapping("/update-event")
    public ResponseEntity<?> updateEvents() {

        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEvents(@PathVariable String id) {

        return null;
    }
}