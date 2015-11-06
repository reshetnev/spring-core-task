package com.epam.reshetnev.spring.core.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.TicketDao;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.google.common.base.Preconditions;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger log = Logger.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketDao ticketDao;

    @Override
    public void save(Ticket ticket) {
        ticketDao.save(ticket);
    }

    @Override
    public void delete(Ticket ticket) {
        ticketDao.delete(ticket);
    }

    @Override
    public Ticket getById(Integer id) {
        return ticketDao.getById(id);
    }

    @Override
    public Ticket getByEventAndSeat(Event event, Integer seat) {
        Optional<Ticket> ticket = getAll()
                .stream()
                .filter(t -> (t.getEventId().equals(event.getId())
                        && t.getSeat().equals(seat)))
                .findFirst();

        if (!ticket.isPresent()) {
            log.info("Ticket is not found with (event, seat): " + event + " " + seat);
            return null;
        }

        return ticket.get();
    }

    @Override
    public List<Ticket> getAll() {
        return ticketDao.getAll();
    }

    @Override
    public List<Ticket> getAllByEventAndSeats(Event event, List<Integer> seats) {
        return getAll()
                .stream()
                .filter(t -> (t.getEventId().equals(event.getId())
                        && (seats.contains(t.getSeat()))))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Ticket ticket) {
        Preconditions.checkNotNull(ticket.getId(), "Ticket id should not be null");
        ticketDao.update(ticket);
    }

}
