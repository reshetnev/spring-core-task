package com.epam.reshetnev.spring.core.aspect;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.google.common.collect.Maps;

@Component
@Aspect
public class CounterAspect {

    private Map<String, Integer> counterGetEventByName = Maps.newHashMap();

    private Map<String, Integer> counterGetTicketPrices = Maps.newHashMap();

    private Map<Integer, Integer> counterBookTicket = Maps.newHashMap();

    public Map<String, Integer> getCounterGetEventByName() {
        return counterGetEventByName;
    }

    public void setCounterGetEventByName(Map<String, Integer> counterGetEventByName) {
        this.counterGetEventByName = counterGetEventByName;
    }

    public Map<String, Integer> getCounterGetTicketPrices() {
        return counterGetTicketPrices;
    }

    public void setCounterGetTicketPrices(Map<String, Integer> counterGetTicketPrices) {
        this.counterGetTicketPrices = counterGetTicketPrices;
    }

    public Map<Integer, Integer> getCounterBookTicket() {
        return counterBookTicket;
    }

    public void setCounterBookTicket(Map<Integer, Integer> counterBookTicket) {
        this.counterBookTicket = counterBookTicket;
    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.service.impl.EventServiceImpl.getByName(..))")
    private void eventServiceGetByNameMethod() {
    }

    @AfterReturning("eventServiceGetByNameMethod() && args(name)")
    public void countEventGetByName(JoinPoint jp, String name) {

        if (!counterGetEventByName.containsKey(name)) {
            counterGetEventByName.put(name, 0);
        }

        counterGetEventByName.put(name, counterGetEventByName.get(name)+1);
    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.service.impl.BookingServiceImpl.getTicketPrices(..))")
    private void bookingServiceGetTicketPricesMethod() {
    }

    @AfterReturning("bookingServiceGetTicketPricesMethod() && args(event, date, seats, user)")
    public void countGetTicketPrices(JoinPoint jp, Event event, LocalDate date, List<Integer> seats, User user) {
        String eventName = event.getName();

        if (!counterGetTicketPrices.containsKey(eventName)) {
            counterGetTicketPrices.put(eventName, 0);
        }

        counterGetTicketPrices.put(eventName, counterGetTicketPrices.get(eventName)+1);
    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.service.impl.BookingServiceImpl.bookTicket(..))")
    private void bookingServiceBookTicketMethod() {
    }

    @AfterReturning("bookingServiceBookTicketMethod() && args(user, ticket)")
    public void countBookTicket(JoinPoint jp, User user, Ticket ticket) {
        Integer eventId = ticket.getEventId();

        if (!counterBookTicket.containsKey(eventId)) {
            counterBookTicket.put(eventId, 0);
        }

        counterBookTicket.put(eventId, counterBookTicket.get(eventId)+1);
    }

}
