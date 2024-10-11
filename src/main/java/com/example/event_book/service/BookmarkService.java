package com.example.event_book.service;

import com.example.event_book.dto.BookmarkDTO;
import com.example.event_book.exception.ResourceNotFoundException;
import com.example.event_book.model.Bookmark;
import com.example.event_book.model.Event;
import com.example.event_book.model.User;
import com.example.event_book.repository.BookmarkRepository;
import com.example.event_book.repository.EventRepository;
import com.example.event_book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    //Get all bookmark by User
    public List<BookmarkDTO> getUserBookmarks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Bookmark> bookmarks = bookmarkRepository.findByUser(user);
        List<BookmarkDTO> bookmarkDTOs = new ArrayList<>();

        for (Bookmark bookmark : bookmarks) {
            Event event = bookmark.getEvent();
            BookmarkDTO bookmarkDTO = new BookmarkDTO();

            bookmarkDTO.setBookmarkId(bookmark.getId());
            bookmarkDTO.setUserId(user.getId());
            bookmarkDTO.setEventName(event.getName());
            bookmarkDTO.setEventCategory(event.getCategory());
            bookmarkDTO.setEventLocation(event.getLocation());
            bookmarkDTO.setEventCity(event.getCity());
            bookmarkDTO.setEventPoster(event.getPoster());
            bookmarkDTO.setEventDate(String.valueOf(event.getDate()));
            bookmarkDTO.setEventTime(String.valueOf(event.getTime()));

            bookmarkDTOs.add(bookmarkDTO);
        }

        return bookmarkDTOs;
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

    public boolean isEventBookmarked(Long userId, Long eventId) {
        return bookmarkRepository.existsByUserIdAndEventId(userId, eventId);
    }
}
