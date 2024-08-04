package com.eventmanagement.services;

import com.eventmanagement.entities.Event;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.OrganizerRepository;
import com.eventmanagement.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private OrganizerRepository organizerRepository;

    public Event createNewEvent(Event event) {

        if (event.getOrganizer() != null) {
            organizerRepository.save(event.getOrganizer());
        }
        if (event.getVenue() != null) {
            venueRepository.save(event.getVenue());
        }
        return eventRepository.save(event);
    }

    public List<Event> getAllEvent() {
        return eventRepository.findAll();

    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setEventName(eventDetails.getEventName());
            existingEvent.setEventDate(eventDetails.getEventDate());
            existingEvent.setDescription(eventDetails.getDescription());
            if (eventDetails.getOrganizer() != null) {
                organizerRepository.save(eventDetails.getOrganizer());
                existingEvent.setOrganizer(eventDetails.getOrganizer());
            }
            if (eventDetails.getVenue() != null) {
                venueRepository.save(eventDetails.getVenue());
                existingEvent.setVenue(eventDetails.getVenue());
            }
            return eventRepository.save(existingEvent);
        } else {
            // Handle the case where the event does not exist
            return null;
        }
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> findByVenue_Id(Long venueId) {
        return eventRepository.findByVenue_Id(venueId);

    }


    public List<Event> findByOrganizer_Id(Long organizerId) {
        return eventRepository.findByOrganizer_Id(organizerId);
    }
}
