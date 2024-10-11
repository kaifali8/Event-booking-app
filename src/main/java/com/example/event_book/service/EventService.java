package com.example.event_book.service;

import com.example.event_book.dto.EventDTO;
import com.example.event_book.model.Event;
import com.example.event_book.repository.EventRepository;
import com.example.event_book.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;


    private EventDTO convertToEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setCategory(event.getCategory());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocation(event.getLocation());
        eventDTO.setCity(event.getCity());
        eventDTO.setDate(event.getDate());
        eventDTO.setTime(event.getTime());
        eventDTO.setPoster(event.getPoster());
        eventDTO.setCover(event.getCover());
        eventDTO.setPrice(event.getPrice().toBigInteger().doubleValue());
        eventDTO.setAvailableSeats(event.getAvailableSeats());
        return eventDTO;
    }

    // Convert EventDTO to Event (useful for creating or updating events)
    private Event convertToEntity(Event eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setName(eventDTO.getName());
        event.setCategory(eventDTO.getCategory());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setCity(eventDTO.getCity());
        event.setDate(eventDTO.getDate());
        event.setTime(eventDTO.getTime());
        event.setPoster(eventDTO.getPoster());
        event.setCover(eventDTO.getCover());
        event.setPrice(BigDecimal.valueOf(eventDTO.getPrice().doubleValue()));
        event.setAvailableSeats(eventDTO.getAvailableSeats());
        return event;
    }

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Create a new Event from DTO
    public EventDTO createEvent(Event eventDTO){
        Event event = convertToEntity(eventDTO);
        Event savedEvent = eventRepository.save(event);
        return convertToEventDTO(savedEvent);
    }

    // Get a single Event by ID, return as EventDTO
    public EventDTO getEventById(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));
        return convertToEventDTO(event);
    }


    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList());
    }


    // Update an existing Event with EventDTO
    public EventDTO updateEvent(Long id, Event updatedEventDTO){
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));

        existingEvent.setName(updatedEventDTO.getName());
        existingEvent.setDescription(updatedEventDTO.getDescription());
        existingEvent.setLocation(updatedEventDTO.getLocation());
        existingEvent.setPrice(BigDecimal.valueOf(updatedEventDTO.getPrice().doubleValue()));
        existingEvent.setDate(updatedEventDTO.getDate());

        Event updatedEvent = eventRepository.save(existingEvent);
        return convertToEventDTO(updatedEvent);
    }

    // Delete an Event
    public void deleteEvent(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));
        eventRepository.delete(event);
    }

    // Search events by keyword, return as EventDTOs
    public List<EventDTO> searchEvent(String keyword){
        List<Event> events = eventRepository.findByNameContainingIgnoreCase(keyword);
        return events.stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList());
    }

    // Search events by date, return as EventDTOs
    public List<EventDTO> searchEventByDate(LocalDate date){
        List<Event> events = eventRepository.findByDate(date);
        return events.stream()
                .map(this::convertToEventDTO)
                .collect(Collectors.toList());
    }
}
