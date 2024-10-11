package com.example.event_book.controller;

import com.example.event_book.dto.BookingDTO;
import com.example.event_book.model.Booking;
import com.example.event_book.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //Create a new booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long userId,
            @RequestParam Long eventId,
            @RequestParam int numberOfTickets
    ){
        Booking booking=bookingService.createBooking(userId, eventId, numberOfTickets);
        return ResponseEntity.status(201).body(booking);
    }

    //Get all bookings for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDTO>> getUserBookings(@PathVariable Long userId){
        List<BookingDTO> bookings=bookingService.getUserBookings(userId);
        return ResponseEntity.ok(bookings);
    }

    // Get all bookings for a specific event (Optional: useful for admin/event manager)
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<BookingDTO>> getEventBookings(@PathVariable Long eventId) {
        List<BookingDTO> bookings = bookingService.getEventBookings(eventId);
        return ResponseEntity.ok(bookings);
    }

    // Update booking (e.g., change number of tickets)
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long bookingId,
            @RequestParam int numberOfTickets
    ) {
        Booking updatedBooking = bookingService.updateBooking(bookingId, numberOfTickets);
        return ResponseEntity.ok(updatedBooking);
    }

    //Delete a booking
    @DeleteMapping
    public ResponseEntity<Void> deleteBooking(
            @RequestParam Long userId,
            @RequestParam Long eventId
    ){
        bookingService.deleteBooking(userId,eventId);
        return ResponseEntity.noContent().build();
    }

}
