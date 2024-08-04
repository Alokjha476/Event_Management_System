package com.eventmanagement.controller;

import com.eventmanagement.entities.Venue;
import com.eventmanagement.services.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;

    @PostMapping
    public ResponseEntity<Object> createVenue(@RequestBody Venue venue) {
        Venue venue1 = venueService.createNewVenue(venue);
        return new ResponseEntity<>(venue1, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVenueById(@PathVariable Long id) {
        Optional<Venue> venue = venueService.getById(id);
        return new ResponseEntity<>(venue, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVenue(@PathVariable Long id, @RequestBody Venue venue) {
        Venue venue1 = venueService.updateVenue(id, venue);
        return new ResponseEntity<>(venue, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVenue(@PathVariable Long id) {
        venueService.deleteVenue(id);
        return new ResponseEntity<>("Venue Deleted", HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<Object> deleteVenue() {
        venueService.deleteAll();
        return new ResponseEntity<>("Venue Deleted", HttpStatus.CREATED);

    }


}
