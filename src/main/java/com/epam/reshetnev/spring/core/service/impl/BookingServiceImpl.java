package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.domain.Auditorium;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.AuditoriumService;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.DiscountService;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.google.common.collect.Lists;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = Logger.getLogger(BookingServiceImpl.class);

    @Value("${vip.increase}")
    private Double vipIncrease;

    @Autowired
    private DiscountService discountService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private TicketService ticketService;

    @Override
    public List<Double> getTicketPrices(Event event, LocalDate date, List<Integer> seats, User user) {

        List<Double> prices = Lists.newArrayList();

        for (int i = 0; i < seats.size(); i++) {

            Double price = event.getBasePrice();

            Integer seat = seats.get(i);

            Auditorium auditorium = auditoriumService.getByName(event.getAuditorium());

            if (auditorium.getVipSeats().contains(seat)) {
                price += event.getBasePrice()*vipIncrease/100;
            }

            Integer ordinalNumberTicket = i+1;

            discountService.checkDiscounts(user, event, date, ordinalNumberTicket);

            price -= discountService.getDiscount(user, event, date);

            prices.add(price);
        }

        return prices;
    }

    @Override
    public void bookTicket(User user, Ticket ticket) {

        if (!ticket.getIsPurchased()) {
            ticket.setIsPurchased(true);
            if (user.getId() != null) {
                ticket.setUserId(user.getId());
            }
            ticketService.update(ticket);
        } else {
            log.info(ticket + " is booked");
        }

    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event) {
        return ticketService.getAll()
                .stream()
                .filter(t -> t.getEventId().equals(event.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void setTicketPriceToZero(User user, Ticket ticket) {
        log.info("PRICE SET TO ZERO for " + ticket + " for " + user);
    }

}
