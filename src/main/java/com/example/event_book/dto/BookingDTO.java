package com.example.event_book.dto;

import lombok.Data;

@Data
public class BookingDTO {
    private Long bookingId;
    private String eventName;
    private String eventCategory;
    private String eventLocation;
    private String eventCity;
    private String eventPoster;
    private String eventDate;
    private String eventTime;
    private int numberOfTickets;
    private int totalPrice;
}
