package com.example.event_book.repository;

import com.example.event_book.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByNameContainingIgnoreCase(String keyword);

    List<Event> findByDate(LocalDate date);
}
