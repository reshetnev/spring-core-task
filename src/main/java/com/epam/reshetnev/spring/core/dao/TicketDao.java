package com.epam.reshetnev.spring.core.dao;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.Ticket;

public interface TicketDao {

    public void save(Ticket ticket);

    public void delete(Ticket ticket);

    public Ticket getById(Integer id);

    public List<Ticket> getAll();

    public void update(Ticket ticket);
}
