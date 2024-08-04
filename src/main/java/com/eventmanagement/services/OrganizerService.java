package com.eventmanagement.services;

import com.eventmanagement.entities.Organizer;
import com.eventmanagement.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerService {
    @Autowired
    private OrganizerRepository organizerRepository;



    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);

    }

    public Optional<Organizer> getById(Long id) {
        return organizerRepository.findById(id);
    }

    public Organizer updateOrganizer(Long id, Organizer organizer) {
        Optional<Organizer> response = getById(id);
        if (response.isPresent()) {
            Organizer existOrganizer = response.get();
            existOrganizer.setName(organizer.getName());
            existOrganizer.setContactInfo(organizer.getContactInfo());

            organizerRepository.save(existOrganizer);
        }

        return null;
    }

    public void deleteOrganizer() {
        organizerRepository.deleteAll();
    }

    public void deleteOrganizer(Long id) {
        organizerRepository.deleteById(id);
    }
}
