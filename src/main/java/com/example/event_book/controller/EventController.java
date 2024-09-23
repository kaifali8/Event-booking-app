package com.example.event_book.controller;

import com.example.event_book.model.Event;
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
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        Event event=eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    //get all events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events=eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }


    //search for events by keyword
    @GetMapping("/search")
    public ResponseEntity<List<Event>> searchEvents(@RequestParam String keyword){
        List<Event> events=eventService.searchEvent(keyword);
        return ResponseEntity.ok(events);
    }

    //search for events by date
    @GetMapping("/searchByDate")
    public ResponseEntity<List<Event>> searchEventByDate(@RequestParam String date){
        LocalDate eventDate=LocalDate.parse(date);
        List<Event> events=eventService.searchEventByDate(eventDate);
        return ResponseEntity.ok(events);
    }
}