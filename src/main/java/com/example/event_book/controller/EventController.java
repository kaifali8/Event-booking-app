package com.example.event_book.controller;

import com.example.event_book.dto.EventDTO;
import com.example.event_book.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    //get an event by ID
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id){
        EventDTO event=eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    //get all events
    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }


    //search for events by keyword
    @GetMapping("/search")
    public ResponseEntity<List<EventDTO>> searchEvents(@RequestParam String keyword){
        List<EventDTO> events=eventService.searchEvent(keyword);
        return ResponseEntity.ok(events);
    }

    //search for events by date
    @GetMapping("/searchByDate")
    public ResponseEntity<List<EventDTO>> searchEventByDate(@RequestParam String date){
        LocalDate eventDate=LocalDate.parse(date);
        List<EventDTO> events=eventService.searchEventByDate(eventDate);
        return ResponseEntity.ok(events);
    }
}