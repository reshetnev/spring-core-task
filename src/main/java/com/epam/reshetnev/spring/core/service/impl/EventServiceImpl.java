package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.service.EventService;

public class EventServiceImpl implements EventService {

    @Override
    public Event create(Event event) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(Event event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Event getByName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Event> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Event> getForDateRange(LocalDate from, LocalDate to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Event> getNextEvents(LocalDate to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void assignAuditorium(Event event, String auditorium, LocalDateTime date) {
        // TODO Auto-generated method stub
        
    }

}
