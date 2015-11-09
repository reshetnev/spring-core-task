package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.EventDao;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.service.EventService;
import com.google.common.base.Preconditions;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger log = Logger.getLogger(EventServiceImpl.class);

    @Autowired
    private EventDao eventDao;

    @Override
    public void save(Event event) {
        eventDao.save(event);
    }

    @Override
    public void delete(Event event) {
        eventDao.delete(event);
    }

    @Override
    public Event getById(Integer id) {
        return eventDao.getById(id);
    }

    @Override
    public Event getByName(String name) {
        Optional<Event> event = getAll()
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();

        if (!event.isPresent()) {
            log.info("Event is not found with (name): " + name);
            return null;
        }

        return event.get();
    }

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }

    @Override
    public List<Event> getAllForDateRange(LocalDate from, LocalDate to) {
        return getAll()
                .stream()
                .filter(e -> ((e.getDate().compareTo(from) >= 0)
                        && (e.getDate().compareTo(to) <= 0)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllNextEvents(LocalDate to) {
        return getAllForDateRange(LocalDate.now(), to);
    }

    @Override
    public void assignAuditorium(Event event, String auditorium, String date, String time) {
        event.setAuditorium(auditorium);
        event.setDate(LocalDate.parse(date));
        event.setTime(LocalTime.parse(time));
    }

    @Override
    public void update(Event event) {
        Preconditions.checkNotNull(event.getId(), "Event id should not be null");
        eventDao.update(event);
    }

}
