package com.example.event_book.controller;

import com.example.event_book.model.Event;
import com.example.event_book.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/events")
public class AdminEventController {

    private final EventService eventService;

    public AdminEventController(EventService eventService) {
        this.eventService = eventService;
    }

    //Admin: can create new event
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        Event createdEvent=eventService.createEvent(event);
        return ResponseEntity.status(201).body(createdEvent);
    }

    //Admin: Update an existing event
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updatedEvent){
        Event event=eventService.updateEvent(id, updatedEvent);
        return ResponseEntity.ok(event);
    }

    //Admin: Delete an event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    //Admin: View all events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
     List<Event> events=eventService.getAllEvents();
     return ResponseEntity.ok(events);
    }

    //Admin: View event details
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        Event event=eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }
}
