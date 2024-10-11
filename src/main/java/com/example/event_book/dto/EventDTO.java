package com.example.event_book.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Data
public class EventDTO {
    @Setter
    private Long id;
    private String name;
    private String category;
    private String description;
    private String location;
    private String city;
    private LocalDate date;
    private LocalTime time;
    private String poster;
    private String cover;
    private Double price;
    private Integer availableSeats;

}
