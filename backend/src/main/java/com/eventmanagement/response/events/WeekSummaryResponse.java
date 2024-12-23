package com.eventmanagement.response.events;

import com.google.api.services.calendar.model.Event;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Data
public class WeekSummaryResponse {

    private AtomicReference<Long> totalNumberOfHours;
    private List<Event> allEventsThisWeek;
}

