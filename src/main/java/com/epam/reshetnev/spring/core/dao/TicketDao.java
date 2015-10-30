package com.epam.reshetnev.spring.core.dao;

import com.epam.reshetnev.spring.core.domain.Ticket;

public interface TicketDao {

    public Ticket save(Ticket ticket);

    public void delete(Ticket ticket);

    public Ticket getTicketById(Integer id);

    public Iterable<Ticket> getAllTickets();
}
