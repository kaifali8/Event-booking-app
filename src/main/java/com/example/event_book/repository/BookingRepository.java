package com.example.event_book.repository;

import com.example.event_book.model.Booking;
import com.example.event_book.model.Event;
import com.example.event_book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUser(User user);
    Optional<Booking> findByUserAndEvent(User user, Event event);
    void deleteByUserAndEvent(User user, Event event);
    List<Booking> findByEventId(Long eventId);
}
