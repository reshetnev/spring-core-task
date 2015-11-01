package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.DiscountService;
import com.google.common.collect.Lists;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = Logger.getLogger(BookingServiceImpl.class);

    @Value("${vip.increase}")
    private Double vipIncrease;

    @Autowired
    private DiscountService discountService;

    @Override
    public List<Double> getTicketPrices(Event event, LocalDateTime airDateTime, List<Integer> seats, User user) {

        List<Double> prices = Lists.newArrayList();

        for (int i = 0; i < seats.size(); i++) {

            Double price = event.getBasePrice();

            Integer seat = seats.get(i);

            if (event.getAuditorium().getVipSeats().contains(seat)) {
                price += event.getBasePrice()*vipIncrease/100;
            }

            Integer ordinalNumberTicket = i+1;
            
            discountService.checkDiscounts(user, event, airDateTime.toLocalDate(), ordinalNumberTicket);

            price -= discountService.getDiscount(user, event, airDateTime.toLocalDate());

            prices.add(price);
        }

        return prices;
    }

    @Override
    public void bookTicket(User user, Ticket ticket) {

        if (!ticket.getIsPurchased()) {
            ticket.setIsPurchased(true);
            ticket.setUser(user);
            if (user.getId() != null) {
//                user.getBookedTickets().add(ticket);
            }
        } else {
            log.info("Ticket is sold");
        }

    }

    @Override
    public Set<Ticket> getTicketsForEvent(Event event, LocalDateTime airDateTime) {
        Set<Ticket> tickets = event.getTickets()
                .stream()
                .filter(ticket -> (ticket.getIsPurchased()))
                .collect(Collectors.toSet());
        return tickets;
    }

}
