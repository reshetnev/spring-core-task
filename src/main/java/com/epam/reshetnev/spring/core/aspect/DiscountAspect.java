package com.epam.reshetnev.spring.core.aspect;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;

@Aspect
public class DiscountAspect {

    private Map<Class<?>, Integer> counter = new HashMap<Class<?>, Integer>();

    private Map<String, Integer> counterBirthDayGetDiscountForUser = new HashMap<String, Integer>();

    private Map<String, Integer> counterEveryTenGetDiscountForUser = new HashMap<String, Integer>();

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }

    public void setCounter(Map<Class<?>, Integer> counter) {
        this.counter = counter;
    }

    public Map<String, Integer> getCounterBirthDayGetDiscountForUser() {
        return counterBirthDayGetDiscountForUser;
    }

    public void setCounterBirthDayGetDiscountForUser(Map<String, Integer> counterBirthDayGetDiscountForUser) {
        this.counterBirthDayGetDiscountForUser = counterBirthDayGetDiscountForUser;
    }

    public Map<String, Integer> getCounterEveryTenGetDiscountForUser() {
        return counterEveryTenGetDiscountForUser;
    }

    public void setCounterEveryTenGetDiscountForUser(Map<String, Integer> counterEveryTenGetDiscountForUser) {
        this.counterEveryTenGetDiscountForUser = counterEveryTenGetDiscountForUser;
    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.discount.DiscountStrategy.getDiscount(..))")
    private void allGetDiscountMethods() {
    }

    @AfterReturning("allGetDiscountMethods()")
    public void count(JoinPoint jp) {

        Class<?> clazz = jp.getTarget().getClass();

        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }

        counter.put(clazz, counter.get(clazz) + 1);
    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.discount.impl.BirthDayStrategy.getDiscount(..))")
    private void birthDayGetDiscountForUserMethod() {
    }

    @AfterReturning("birthDayGetDiscountForUserMethod() && args(user, event, airDate)")
    public void countBirthDayGetDiscountForUser(JoinPoint jp, User user, Event event, LocalDate airDate) {

        String userEmail = user.getEmail();

        if (!counterBirthDayGetDiscountForUser.containsKey(userEmail)) {
            counterBirthDayGetDiscountForUser.put(userEmail, 0);
        }

        counterBirthDayGetDiscountForUser.put(userEmail, counterBirthDayGetDiscountForUser.get(userEmail) + 1);

    }

    @Pointcut("execution(* com.epam.reshetnev.spring.core.discount.impl.EveryTenStrategy.getDiscount(..))")
    private void everyTenGetDiscountForUserMethod() {
    }

    @AfterReturning("everyTenGetDiscountForUserMethod() && args(user, event, airDate)")
    public void countEveryTenGetDiscountForUser(JoinPoint jp, User user, Event event, LocalDate airDate) {

        String userEmail = user.getEmail();

        if (!counterEveryTenGetDiscountForUser.containsKey(userEmail)) {
            counterEveryTenGetDiscountForUser.put(userEmail, 0);
        }

        counterEveryTenGetDiscountForUser.put(userEmail, counterEveryTenGetDiscountForUser.get(userEmail) + 1);

    }
}
