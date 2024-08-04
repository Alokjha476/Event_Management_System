package com.eventmanagement.services;

import com.eventmanagement.entities.Venue;
import com.eventmanagement.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VenueService {
    @Autowired
    private VenueRepository venueRepository;

    // create the new venue
    public Venue createNewVenue(Venue venue) {
        return venueRepository.save(venue);
    }

    public Optional<Venue> getById(Long id) {
        return venueRepository.findById(id);
    }

    public void deleteVenue(Long id) {
        venueRepository.deleteById(id);
    }

    public void deleteAll(){
        venueRepository.deleteAll();

    }

    public Venue updateVenue(Long id, Venue venue) {
        Optional<Venue> response = getById(venue.getId());
        if (response.isPresent()) {
            Venue existingVenue = response.get();
            existingVenue.setName(venue.getName());
            existingVenue.setLocation(venue.getLocation());
            existingVenue.setCapacity(venue.getCapacity());
            venueRepository.save(existingVenue);
        }

        return null;
    }



}
