package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDateTime;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.BookinService;

public class BookinServiceImpl implements BookinService {

    @Override
    public Integer getTicketPrice(Event event, LocalDateTime airDateTime, Iterable<Integer> seats, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void bbokTicket(User user, Ticket ticket) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterable<Ticket> getTicketsForEvent(Event event, LocalDateTime airDateTime) {
        // TODO Auto-generated method stub
        return null;
    }

}
