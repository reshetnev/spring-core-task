package com.epam.reshetnev.spring.core.dao.impl;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.EventDao;
import com.epam.reshetnev.spring.core.domain.Event;

@Repository
public class EventDaoImpl implements EventDao {

    private ConcurrentMap<String, Event> events = new ConcurrentHashMap<String, Event>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Event save(Event event) {
        while (true) {
            Integer id = generateId();
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

    public static Integer generateId() {
        Random r = new Random();
        return r.ints(1, Integer.MAX_VALUE).limit(1).findFirst().getAsInt();
    }
}
