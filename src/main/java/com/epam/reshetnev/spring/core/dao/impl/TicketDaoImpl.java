package com.epam.reshetnev.spring.core.dao.impl;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.TicketDao;
import com.epam.reshetnev.spring.core.domain.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {

    private ConcurrentMap<String, Ticket> tickets = new ConcurrentHashMap<String, Ticket>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ticket save(Ticket ticket) {
        while (true) {
            Integer id = generateId();
            ticket.setId(id);
            if (tickets.putIfAbsent(id.toString(), ticket) == null) {
                break;
            } else {
                continue;
            }
        }
        return ticket;
    }

    @Override
    public void delete(Ticket ticket) {
        tickets.remove(ticket.getId().toString());

    }

    @Override
    public Ticket getTicketById(Integer id) {
        return tickets.get(id);
    }

    @Override
    public Iterable<Ticket> getAllTickets() {
        return tickets.values();
    }

    public static Integer generateId() {
        Random r = new Random();
        return r.ints(1, Integer.MAX_VALUE).limit(1).findFirst().getAsInt();
    }
}
