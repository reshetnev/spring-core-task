package com.epam.reshetnev.spring.core.service;

import java.time.LocalDate;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;

public interface DiscountService {

    public Double getDiscount(User user, Event event, LocalDate airDate);
}
