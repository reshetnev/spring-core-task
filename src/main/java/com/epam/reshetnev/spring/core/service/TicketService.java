package com.epam.reshetnev.spring.core.service;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;

public interface TicketService {

    public Ticket register(Ticket ticket);

    public Iterable<Ticket> getAllTickets();

    public List<Ticket> getByEvent(Event event, List<Integer> seats);

}
