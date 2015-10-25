package com.epam.reshetnev.spring.core.discount.impl;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;

import com.epam.reshetnev.spring.core.discount.DiscountStrategy;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;

public class EveryTenStrategy implements DiscountStrategy {

    @Value("${every.ten.discount}")
    private Double everyTenDiscount;

    @Override
    public Double getDiscount(User user, Event event, LocalDate airDate) {

        int count = 0;

        Set<Ticket> ticketList = user.getBookedTickets();
        for (Ticket ticket : ticketList) {
            if (ticket.getEvent().equals(event)) {
                count++;
            }
        }
        int quantity = count/10;
        Double discount = quantity*everyTenDiscount/100;

        return discount;
    }

}
