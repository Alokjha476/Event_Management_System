package com.eventmanagement.services;

import com.eventmanagement.entities.Event;
import com.eventmanagement.entities.Organizer;
import com.eventmanagement.entities.Venue;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.OrganizerRepository;
import com.eventmanagement.repository.VenueRepository;
import jakarta.persistence.EntityNotFoundException;
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

            if (eventDetails.getEventName() != null && !eventDetails.getEventName().isEmpty()) {
                existingEvent.setEventName(eventDetails.getEventName());
            }

            if (eventDetails.getEventDate() != null) {
                existingEvent.setEventDate(eventDetails.getEventDate());
            }
            if (eventDetails.getDescription() != null) {
                existingEvent.setDescription(eventDetails.getDescription());
            }

            if (eventDetails.getOrganizer() != null) {
                Organizer organizerDetails = eventDetails.getOrganizer();
                Organizer existingOrganizer = existingEvent.getOrganizer();
                if (existingOrganizer != null && organizerDetails.getId() != null && existingOrganizer.getId().equals(organizerDetails.getId())) {
                    existingOrganizer.setName(organizerDetails.getName());
                    existingOrganizer.setContactInfo(organizerDetails.getContactInfo());
                    organizerRepository.save(existingOrganizer);
                } else {
                    organizerRepository.save(organizerDetails);
                    existingEvent.setOrganizer(organizerDetails);
                }
            }

            if (eventDetails.getVenue() != null) {
                Venue venueDetails = eventDetails.getVenue();
                Venue existingVenue = existingEvent.getVenue();
                if (existingVenue != null && venueDetails.getId() != null && existingVenue.getId().equals(venueDetails.getId())) {
                    existingVenue.setName(venueDetails.getName());
                    existingVenue.setLocation(venueDetails.getLocation());
                    venueRepository.save(existingVenue);
                } else {
                    venueRepository.save(venueDetails);
                    existingEvent.setVenue(venueDetails);
                }
            }

            return eventRepository.save(existingEvent);
        } else {
            throw new EntityNotFoundException("Event with ID " + id + " not found");
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
