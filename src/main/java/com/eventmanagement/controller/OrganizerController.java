package com.eventmanagement.controller;

import com.eventmanagement.entities.Organizer;
import com.eventmanagement.services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
    @Autowired
    private OrganizerService organizerService;
    @PostMapping
    public ResponseEntity<Organizer> createOrganizer(@RequestBody Organizer organizer){
        Organizer organizer1 = organizerService.createOrganizer(organizer);
        return new ResponseEntity<>(organizer1, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrganizer(@PathVariable Long id){
        Optional<Organizer> organizer1 = organizerService.getById(id);
        return new ResponseEntity<>(organizer1, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> getOrganizer(@PathVariable Long id, @RequestBody Organizer organizer) {
        Organizer organizer1 = organizerService.updateOrganizer(id,organizer);
        return new ResponseEntity<>(organizer1, HttpStatus.OK);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOrganizer(@PathVariable Long id){
        organizerService.deleteOrganizer(id);
        return new ResponseEntity<>("Deleted Organizer", HttpStatus.OK);

    }
    @DeleteMapping
    public ResponseEntity<Object> deleteOrganizer(){
        organizerService.deleteOrganizer();
        return new ResponseEntity<>("Deleted Organizer", HttpStatus.OK);

    }



}
