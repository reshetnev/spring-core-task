package com.epam.reshetnev.spring.core.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.TicketDao;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.util.Generator;

@Repository
public class TicketDaoImpl implements TicketDao {

    private Map<String, Ticket> tickets;

    @Override
    public Ticket save(Ticket ticket) {
        while (true) {
            Integer id = Generator.generateId();
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

}
