package com.epam.reshetnev.spring.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.TicketDao;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.google.common.collect.Lists;

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
    public List<Ticket> getByEvent(Event event, List<Integer> seats) {
        List<Ticket> tickets = Lists.newArrayList(getAllTickets())
                .stream()
                .filter(t -> ((t.getEvent().equals(event)) && (seats.contains(t.getSeat()))))
                .collect(Collectors.toList());
        return tickets;
    }

}
