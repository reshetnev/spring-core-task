package com.epam.reshetnev.spring.core.service;

import java.time.LocalDateTime;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;

public interface BookinService {

    public Double getTicketPrice(Event event, LocalDateTime airDateTime, Iterable<Integer> seats, User user);

    public void bookTicket(User user, Ticket ticket);

    public Iterable<Ticket> getTicketsForEvent(Event event, LocalDateTime airDateTime);
}
