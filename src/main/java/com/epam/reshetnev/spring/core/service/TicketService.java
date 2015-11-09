package com.epam.reshetnev.spring.core.service;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;

public interface TicketService {

    public void save(Ticket ticket);

    public void delete(Ticket ticket);

    public Ticket getById(Integer id);

    public Ticket getByEventAndSeat(Event event, Integer seat);

    public List<Ticket> getAll();

    public List<Ticket> getAllByEventAndSeats(Event event, List<Integer> seats);

    public void update(Ticket ticket);

}
