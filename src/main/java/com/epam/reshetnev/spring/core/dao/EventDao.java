package com.epam.reshetnev.spring.core.dao;

import com.epam.reshetnev.spring.core.domain.Event;

public interface EventDao {

    public Event save(Event event);

    public void delete(Event event);

    public Event getEventById(Integer name);

    public Iterable<Event> getAllEvents();

}
