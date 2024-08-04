package com.eventmanagement.repository;

import com.eventmanagement.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByVenue_Id(Long venueId);

    List<Event> findByOrganizer_Id(Long organizerId);
}
