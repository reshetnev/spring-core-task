package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.BookinService;
import com.epam.reshetnev.spring.core.service.DiscountService;

@Service
public class BookinServiceImpl implements BookinService {

    @Autowired
    private DiscountService discountService;

    @Override
    public Double getTicketPrice(Event event, LocalDateTime airDateTime, Iterable<Integer> seats, User user) {
        Double price = event.getBasePrice() - discountService.getDiscount(user, event, airDateTime.toLocalDate());
        return price;
    }

    @Override
    public void bookTicket(User user, Ticket ticket) {

        ticket.getEvent().getPurchasedTickets().add(ticket);

        if (user.getId() != null) {
            user.getBookedTickets().add(ticket);
        }
    }

    @Override
    public Iterable<Ticket> getTicketsForEvent(Event event, LocalDateTime airDateTime) {
        return event.getPurchasedTickets();
    }

}
