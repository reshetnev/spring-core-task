package com.epam.reshetnev.spring.core.discount.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

import com.epam.reshetnev.spring.core.discount.DiscountStrategy;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;

public class EveryTenStrategy implements DiscountStrategy {

    @Value("${every.ten.discount}")
    private Double everyTenDiscount;

    @Override
    public Double getDiscount(User user, Event event, LocalDate airDate) {

        Double discount = event.getBasePrice()*everyTenDiscount/100;

        return discount;
    }

}
