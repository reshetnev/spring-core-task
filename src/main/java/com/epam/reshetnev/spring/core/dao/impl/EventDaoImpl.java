package com.epam.reshetnev.spring.core.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.EventDao;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.util.Generator;
import com.google.common.collect.Maps;

@Repository
public class EventDaoImpl implements EventDao {

    private Map<String, Event> events = Maps.newHashMap();
    
    @Override
    public Event save(Event event) {
        while (true) {
            Integer id = Generator.generateId();
            event.setId(id);
            if (events.putIfAbsent(id.toString(), event) == null) {
                break;
            } else {
                continue;
            }
        }
        return event;
    }

    @Override
    public void delete(Event event) {
        events.remove(event.getId().toString());
    }

    @Override
    public Event getEventById(Integer id) {
        return events.get(id);
    }

    @Override
    public Iterable<Event> getAllEvents() {
        return events.values();
    }

}
