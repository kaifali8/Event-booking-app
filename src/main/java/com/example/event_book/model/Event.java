package com.example.event_book.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, name="eve_time")
    private LocalTime time;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String city;


    @Column(nullable = false)
    private String poster;

    private String cover;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "available_seats")
    @Min(0)
    private int availableSeats;

    @OneToMany(mappedBy = "event")
    @JsonManagedReference
    private List<Booking> bookings;
}
