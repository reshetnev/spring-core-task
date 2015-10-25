package com.epam.reshetnev.spring.core.discount;

import java.time.LocalDate;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;

public interface DiscountStrategy {

    public Double getDiscount(User user, Event event, LocalDate airDate);
}
