package com.epam.reshetnev.spring.core.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.TicketDao;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.google.common.collect.Sets;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Ticket register(Ticket ticket) {
        return ticketDao.save(ticket);
    }

    @Override
    public Iterable<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    @Override
    public Ticket getBySeat(Integer seat) {
        Set<Ticket> tickets = Sets.newHashSet(getAllTickets());
        Optional<Ticket> ticket = tickets
                .stream()
                .filter(t -> (t.getSeat().equals(seat)))
                .findFirst();
        return ticket.get();
    }

}
