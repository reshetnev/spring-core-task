package com.epam.reshetnev.spring.core.service;

import java.time.LocalDate;
import java.util.List;

import com.epam.reshetnev.spring.core.domain.Event;

public interface EventService {

    public void save(Event event);

    public void delete(Event event);

    public Event getById(Integer id);

    public Event getByName(String name);

    public List<Event> getAll();

    public List<Event> getAllForDateRange(LocalDate from, LocalDate to);

    public List<Event> getAllNextEvents(LocalDate to);

    public void assignAuditorium(Event event, String auditorium, String date, String time);

    public void update(Event event);
}
