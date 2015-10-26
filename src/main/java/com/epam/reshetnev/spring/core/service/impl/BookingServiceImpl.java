package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.DiscountService;
import com.epam.reshetnev.spring.core.service.EventService;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = Logger.getLogger(BookingServiceImpl.class);

    @Autowired
    private DiscountService discountService;

    @Autowired
    private EventService eventService;

    @Override
    public Double getTicketPrice(Event event, LocalDateTime airDateTime, Iterable<Integer> seats, User user) {
        Double price = event.getBasePrice() - discountService.getDiscount(user, event, airDateTime.toLocalDate());
        return price;
    }

    @Override
    public void bookTicket(User user, Ticket ticket) {

        if (!ticket.getEvent().getPurchasedTickets().contains(ticket)) {
            ticket.getEvent().getPurchasedTickets().add(ticket);
            if (user.getId() != null) {
                user.getBookedTickets().add(ticket);
            }
        } else {
            log.info("Ticket is sold");
        }


    }

    @Override
    public Iterable<Ticket> getTicketsForEvent(Event event, LocalDateTime airDateTime) {
        return event.getPurchasedTickets();
    }

}
