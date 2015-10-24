package com.epam.reshetnev.spring.core.discount;

import java.time.LocalDate;

import com.epam.reshetnev.spring.core.domain.User;

public interface DiscountStrategy {

    public int getDiscount(User user, LocalDate airDate);
}
