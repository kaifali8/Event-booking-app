package com.example.event_book.service;

import com.example.event_book.exception.ResourceNotFoundException;
import com.example.event_book.model.Bookmark;
import com.example.event_book.model.Event;
import com.example.event_book.model.User;
import com.example.event_book.repository.BookmarkRepository;
import com.example.event_book.repository.EventRepository;
import com.example.event_book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    //Create bookmark for user and event
    public Bookmark createBookmark(Long userId, Long eventId){
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));
        Event event=eventRepository.findById(eventId)
                .orElseThrow(()-> new ResourceNotFoundException("Event not found with id:"+eventId));
        if (bookmarkRepository.findByUserAndEvent(user,event).isPresent()){
            throw new IllegalArgumentException("Bookmark already existed for this event");
        }
        Bookmark bookmark=new Bookmark();
        bookmark.setUser(user);
        bookmark.setEvent(event);

        return bookmarkRepository.save(bookmark);
    }

    //Get a bookmark by User
    public List<Bookmark> getUserBookmarks(Long userId){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id:"+userId));
        return bookmarkRepository.findByUser(user);
    }

    //Delete a Bookmark
    public void deleteBookmark(Long userId,Long eventId){
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id:"+userId));
        Event event=eventRepository.findById(eventId)
                .orElseThrow(()->new ResourceNotFoundException("Event not found with id:"+eventId));
        Bookmark bookmark=bookmarkRepository.findByUserAndEvent(user,event)
                .orElseThrow(()->new ResourceNotFoundException("Bookmark not found for this event"));
        bookmarkRepository.delete(bookmark);
    }

}
