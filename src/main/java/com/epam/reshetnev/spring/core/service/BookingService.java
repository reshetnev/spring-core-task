package com.epam.reshetnev.spring.core.service;

import java.time.LocalDateTime;
import java.util.List;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;

public interface BookingService {

    public List<Double> getTicketPrices(Event event, LocalDateTime airDateTime, List<Integer> seats, User user);

    public void bookTicket(User user, Ticket ticket);

    public Iterable<Ticket> getTicketsForEvent(Event event, LocalDateTime airDateTime);

}
