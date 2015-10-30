package com.epam.reshetnev.spring.core.aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CounterAspect {

    private Map<String, Integer> counterGetEventByName = new HashMap<String, Integer>();

    private Map<String, Integer> counterGetTicketPrices = new HashMap<String, Integer>();

    private Map<String, Integer> counterBookTicket = new HashMap<String, Integer>();

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

    public Map<String, Integer> getCounterBookTicket() {
        return counterBookTicket;
    }

    public void setCounterBookTicket(Map<String, Integer> counterBookTicket) {
        this.counterBookTicket = counterBookTicket;
    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.service.impl.EventServiceImpl.getByName(..))")
    private void eventServiceGetByNameMethod() {
    }

    @AfterReturning("eventServiceGetByNameMethod() && args(name)")
    public void count(JoinPoint jp, String name) {

        if (!counterGetEventByName.containsKey(name)) {
            counterGetEventByName.put(name, 0);
        }

        counterGetEventByName.put(name, counterGetEventByName.get(name)+1);
    }
}
