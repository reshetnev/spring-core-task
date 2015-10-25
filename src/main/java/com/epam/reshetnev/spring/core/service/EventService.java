package com.epam.reshetnev.spring.core.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.epam.reshetnev.spring.core.domain.Event;

public interface EventService {

    public Event create(Event event);

    public void remove(Event event);

    public Event getByName();

    public Iterable<Event> getAll();

    public Optional<Event> getForDateRange(LocalDate from, LocalDate to);

    public Optional<Event> getNextEvents(LocalDate to);

    public void assignAuditorium(Event event, String auditorium, LocalDateTime date);
}
