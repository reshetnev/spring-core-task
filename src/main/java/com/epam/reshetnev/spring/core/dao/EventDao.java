package com.epam.reshetnev.spring.core.dao;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.Event;

public interface EventDao {

    public void save(Event event);

    public void delete(Event event);

    public Event getById(Integer id);

    public List<Event> getAll();

    public void update(Event event);
}
