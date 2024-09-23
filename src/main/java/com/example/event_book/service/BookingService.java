package com.example.event_book.service;

import com.example.event_book.exception.ResourceNotFoundException;
import com.example.event_book.model.Booking;
import com.example.event_book.model.Event;
import com.example.event_book.model.User;
import com.example.event_book.repository.BookingRepository;
import com.example.event_book.repository.EventRepository;
import com.example.event_book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    //create a new booking with user
    public Booking createBooking(Long userId,Long eventId,int numberOfTickets){
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));
        Event event=eventRepository.findById(eventId)
                .orElseThrow(()-> new ResourceNotFoundException("Event not found with id:"+eventId));

        // Check if a booking for this user and event already exists
        if (bookingRepository.findByUserAndEvent(user, event).isPresent()) {
            throw new IllegalArgumentException("Booking already exists for this event");
        }

        Booking booking=new Booking();
        booking.setUser(user);
        booking.setEvent(event);
        booking.setNumberOfTickets(numberOfTickets);

        return bookingRepository.save(booking);
    }

    //get all booking of a user
    public List<Booking> getUserBookings(Long userId){
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));

        return bookingRepository.findByUser(user);
    }


    //delete a booking
    public void deleteBooking(Long userId,Long eventId){
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));
        Event event=eventRepository.findById(eventId)
                .orElseThrow(()-> new ResourceNotFoundException("Event not found with id:"+eventId));

        Booking booking=bookingRepository.findByUserAndEvent(user,event)
                .orElseThrow(()-> new ResourceNotFoundException("Booking not found for this event"));

        bookingRepository.delete(booking);
    }

    //
    public List<Booking> getEventBookings(Long eventId) {
        return bookingRepository.findByEventId(eventId);
    }

    //Update booking (eg. number of tickets)
    public Booking updateBooking(Long bookingId, int numberOfTickets) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            // Update the number of tickets
            booking.setNumberOfTickets(numberOfTickets);

            // Save the updated booking
            return bookingRepository.save(booking);
        } else {
            // Handle the case when booking is not found (could throw a custom exception)
            throw new RuntimeException("Booking not found with id: " + bookingId);
        }
    }
}
