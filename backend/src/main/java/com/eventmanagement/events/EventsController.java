package com.eventmanagement.events;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController("/apiv1/events")
public class EventsController {

    @GetMapping
    public ResponseEntity<?> getAllEvents(@AuthenticationPrincipal OAuth2User principle) {

        p

        return null;
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