package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.EventDao;
import com.epam.reshetnev.spring.core.domain.Auditorium;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.service.EventService;
import com.google.common.collect.Lists;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;
    
    @Override
    public Event create(Event event) {
        return eventDao.save(event);
    }

    @Override
    public void remove(Event event) {
        eventDao.delete(event);
    }

    @Override
    public Event getByName(String name) {
        Event eventByName = null;

        for (Event event : getAll()) {
            if (event.getName().equals(name)) {
                eventByName = event;
            }
        }

        return eventByName;
    }

    @Override
    public List<Event> getAll() {
        return Lists.newArrayList(eventDao.getAllEvents());
    }

    @Override
    public List<Event> getForDateRange(LocalDate from, LocalDate to) {
        List<Event> events = Lists.newArrayList(getAll())
                .stream()
                .filter(e -> (e.getAirDateTime().toLocalDate().compareTo(from) >= 0) &&
                    (e.getAirDateTime().toLocalDate().compareTo(to) <= 0))
                .collect(Collectors.toList());
        return events;
    }

    @Override
    public List<Event> getNextEvents(LocalDate to) {
        return getForDateRange(LocalDate.now(), to);
    }

    @Override
    public void assignAuditorium(Event event, Auditorium auditorium, LocalDateTime airDateTime) {
        event.setAuditorium(auditorium);
        event.setAirDateTime(airDateTime);
    }

}
