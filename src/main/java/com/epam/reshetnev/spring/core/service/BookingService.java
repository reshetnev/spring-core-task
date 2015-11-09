package com.epam.reshetnev.spring.core.service;

import java.time.LocalDate;
import java.util.List;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;

public interface BookingService {

    public List<Double> getTicketPrices(Event event, LocalDate date, List<Integer> seats, User user);

    public void bookTicket(User user, Ticket ticket);

    public List<Ticket> getTicketsForEvent(Event event);

    public void setTicketPriceToZero(User user, Ticket ticket);
}
