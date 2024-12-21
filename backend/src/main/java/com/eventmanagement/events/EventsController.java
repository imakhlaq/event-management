package com.eventmanagement.events;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/apiv1/events/")
public class EventsController {

    @GetMapping
    public ResponseEntity<?> getAllEvents() {

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvents(@PathVariable String id) {

        return null;
    }
}