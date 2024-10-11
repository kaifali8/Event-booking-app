package com.example.event_book.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private String photo;
    private String phone;
    private String address;
    private String gender;
    private LocalDate dob;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'USER'")
    private String role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-booking")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-bookmark") // Handles the forward relationship
    private List<Bookmark> bookmarks;
}
