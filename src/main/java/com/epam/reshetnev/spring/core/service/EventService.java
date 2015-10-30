package com.epam.reshetnev.spring.core.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.epam.reshetnev.spring.core.domain.Auditorium;
import com.epam.reshetnev.spring.core.domain.Event;

public interface EventService {

    public Event create(Event event);

    public void remove(Event event);

    public Event getByName(String name);

    public List<Event> getAll();

    public List<Event> getForDateRange(LocalDate from, LocalDate to);

    public List<Event> getNextEvents(LocalDate to);

    public void assignAuditorium(Event event, Auditorium auditorium, LocalDateTime airDateTime);
}
