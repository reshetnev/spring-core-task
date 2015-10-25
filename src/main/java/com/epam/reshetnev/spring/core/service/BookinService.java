package com.epam.reshetnev.spring.core.service;

import java.time.LocalDateTime;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;

public interface BookinService {

    public Integer getTicketPrice(Event event, LocalDateTime airDateTime, Iterable<Integer> seats, User user);

    public void bbokTicket(User user, Ticket ticket);

    public Iterable<Ticket> getTicketsForEvent(Event event, LocalDateTime airDateTime);
}
