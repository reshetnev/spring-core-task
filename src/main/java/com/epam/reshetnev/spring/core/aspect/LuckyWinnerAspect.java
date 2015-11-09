package com.epam.reshetnev.spring.core.aspect;

import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.google.common.collect.Maps;

@Component
@Aspect
public class LuckyWinnerAspect {

    private Map<String, Integer> counterLuckyWinner = Maps.newHashMap();

    public Map<String, Integer> getCounterLuckyWinner() {
        return counterLuckyWinner;
    }

    public void setCounterLuckyWinner(Map<String, Integer> counterLuckyWinner) {
        this.counterLuckyWinner = counterLuckyWinner;
    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.service.impl.BookingServiceImpl.setTicketPriceToZero(..))")
    private void bookingServiceSetTicketPriceToZeroMethod() {
    }

    @AfterReturning("bookingServiceSetTicketPriceToZeroMethod() && args(user, ticket)")
    public void countSetTicketPriceToZero(JoinPoint jp, User user, Ticket ticket) {
        String userEmail = user.getEmail();

        if (!counterLuckyWinner.containsKey(userEmail)) {
            counterLuckyWinner.put(userEmail, 0);
        }

        counterLuckyWinner.put(userEmail, counterLuckyWinner.get(userEmail)+1);
    }
}
