package com.archit.eventbooking.service;

import com.archit.eventbooking.dto.EventRequest;
import com.archit.eventbooking.dto.EventResponse;

public interface EventService {
    EventResponse createEvent(EventRequest eventRequest);

}
