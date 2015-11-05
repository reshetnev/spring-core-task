package com.epam.reshetnev.spring.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.EventDao;
import com.epam.reshetnev.spring.core.dao.impl.rowmapper.EventRowMapper;
import com.epam.reshetnev.spring.core.domain.Event;

@Repository
public class EventDaoImpl implements EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Event event) {
        jdbcTemplate.update("INSERT INTO events (id, name, airDate, airTime, basePrice, rating, auditorium) VALUES (?,?,?,?,?,?,?)",
                null,
                event.getName(),
                event.getDate().toString(),
                event.getTime().toString() + ":00",
                event.getBasePrice(),
                event.getRating().name(),
                event.getAuditorium());
    }

    @Override
    public void delete(Event event) {
        jdbcTemplate.update("DELETE FROM events WHERE events.id = ?",
                event.getId());
    }

    @Override
    public Event getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM events WHERE events.id = ?",
                new Object[] {id},
                new EventRowMapper());
    }

    @Override
    public List<Event> getAll() {
        return jdbcTemplate.query("SELECT * FROM events",
                new EventRowMapper());
    }

    @Override
    public void update(Event event) {
        jdbcTemplate.update("UPDATE events SET name = ?, airDate = ?, airTime = ?, basePrice = ?, rating = ?, auditorium = ? WHERE events.id = ?",
                event.getName(),
                event.getDate().toString(),
                event.getTime().toString(),
                event.getBasePrice(),
                event.getRating().name(),
                event.getAuditorium(),
                event.getId());
    }

}
