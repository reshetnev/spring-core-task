package com.epam.reshetnev.spring.core.service;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;

public interface TicketService {

    public Ticket register(Ticket ticket);

    public Iterable<Ticket> getAllTickets();

    public Ticket getByEvent(Event event);

}
