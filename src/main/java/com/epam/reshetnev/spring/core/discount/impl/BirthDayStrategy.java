package com.epam.reshetnev.spring.core.discount.impl;

import java.time.LocalDate;

import com.epam.reshetnev.spring.core.discount.DiscountStrategy;
import com.epam.reshetnev.spring.core.domain.User;

public class BirthDayStrategy implements DiscountStrategy {

    @Override
    public int getDiscount(User user, LocalDate airDate) {
        // TODO Auto-generated method stub
        return 0;
    }

}
