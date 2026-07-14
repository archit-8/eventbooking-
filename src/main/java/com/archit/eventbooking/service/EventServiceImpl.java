package com.archit.eventbooking.service;


import com.archit.eventbooking.dto.EventRequest;
import com.archit.eventbooking.dto.EventResponse;
import com.archit.eventbooking.entity.Event;
import com.archit.eventbooking.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl  implements   EventService {

    private final EventRepository eventRepository;
     public EventServiceImpl(EventRepository eventRepository){

         this.eventRepository=eventRepository;
     }

     @Override
     public EventResponse createEvent(EventRequest eventRequest){
       Event event = new Event();
       event.setTitle(eventRequest.getTitle());
       event.setVenue(eventRequest.getVenue());
       event.setDescription(eventRequest.getDescription());
       event.setEventDate(eventRequest.getEventDate());
       event.setAvailableSeats(eventRequest.getAvailableSeat());
       event.setTicketPrice(eventRequest.getTicketPrice());

       Event savedEvent= eventRepository.save(event);

       EventResponse response= new EventResponse();

       response.setId(savedEvent.getId());
       response.setTitle(savedEvent.getTitle());
     }


}
