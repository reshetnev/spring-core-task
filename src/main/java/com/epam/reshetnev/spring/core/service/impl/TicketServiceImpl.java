package com.epam.reshetnev.spring.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.TicketDao;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;
    
    @Override
    public Ticket register(Ticket ticket) {
        return ticketDao.save(ticket);
    }

}
