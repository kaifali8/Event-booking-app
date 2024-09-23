package com.example.event_book.service;

import com.example.event_book.model.Event;
import com.example.event_book.repository.EventRepository;
import com.example.event_book.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public Event getEventById(Long id){
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event updateEvent(Long id,Event updatedEvent){
        Event existingEvent=getEventById(id);
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setPrice(updatedEvent.getPrice());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setDate(updatedEvent.getDate());

        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Long id){
        Event event=getEventById(id);
        eventRepository.delete(event);
    }

    public List<Event> searchEvent(String keyword){
        return eventRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Event> searchEventByDate(LocalDate date){
        return eventRepository.findByDate(date);
    }
}
