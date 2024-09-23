package com.example.event_book.repository;

import com.example.event_book.model.Bookmark;
import com.example.event_book.model.Event;
import com.example.event_book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    List<Bookmark> findByUser(User user);
    Optional<Bookmark> findByUserAndEvent(User user, Event event);
    void deleteByUserAndEvent(User user, Event event);
}
