package com.epam.reshetnev.spring.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.TicketDao;
import com.epam.reshetnev.spring.core.dao.impl.rowmapper.TicketRowMapper;
import com.epam.reshetnev.spring.core.domain.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Ticket ticket) {
        jdbcTemplate.update("INSERT INTO tickets (id, eventId, userId, seat, isPurchased) VALUES (?,?,?,?,?)",
                null,
                ticket.getEventId(),
                ticket.getUserId(),
                ticket.getSeat(),
                ticket.getIsPurchased());
    }

    @Override
    public void delete(Ticket ticket) {
        jdbcTemplate.update("DELETE FROM tickets WHERE tickets.id = ?",
                ticket.getId());
    }

    @Override
    public Ticket getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tickets WHERE tickets.id = ?",
                new Object[] {id},
                new TicketRowMapper());
    }

    @Override
    public List<Ticket> getAll() {
        return jdbcTemplate.query("SELECT * FROM tickets",
                new TicketRowMapper());
    }

    @Override
    public void update(Ticket ticket) {
        jdbcTemplate.update("UPDATE tickets SET eventId = ?, userId = ?, seat = ?, isPurchased = ? WHERE tickets.id = ?",
                ticket.getEventId(),
                ticket.getUserId(),
                ticket.getSeat(),
                ticket.getIsPurchased(),
                ticket.getId());
    }

}
