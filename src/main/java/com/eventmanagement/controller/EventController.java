package com.eventmanagement.controller;

import com.eventmanagement.entities.Event;
import com.eventmanagement.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;


    @PostMapping
    public ResponseEntity<Object> createEvent(@RequestBody Event event) {
        Event event1 = eventService.createNewEvent(event);
        return new ResponseEntity<>(event1, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Object> getEvent() {
        List<Event> event1 = eventService.getAllEvent();
        return new ResponseEntity<>(event1, HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Object> updateEvent(@RequestBody Event event) {
        Event event1 = eventService.updateEvent(event);
        return new ResponseEntity<>(event1, HttpStatus.OK);


    }
    @DeleteMapping
    public ResponseEntity<Object> deleteAll() {
        eventService.deleteAll();
        return new ResponseEntity<>("All Event deleted", HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteByIdEvent(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return new ResponseEntity<>("Event deleted", HttpStatus.OK);

    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<Object> listOfEvent(@PathVariable Long venueId) {
        List<Event> eventList = eventService.findByVenue_Id(venueId);
        return new ResponseEntity<>(eventList, HttpStatus.OK);

    }

    @GetMapping("/organizer/{organizerId}")
    public ResponseEntity<Object> listOfEvents(@PathVariable Long organizerId) {
        List<Event> eventList = eventService.findByOrganizer_Id(organizerId);
        return new ResponseEntity<>(eventList, HttpStatus.OK);

    }
}
