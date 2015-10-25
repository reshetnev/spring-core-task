package com.epam.reshetnev.spring.core.service;

import java.time.LocalDateTime;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;

public interface DiscountService {

    public int getDiscount(User user, Event event, LocalDateTime airDateTime);
}
