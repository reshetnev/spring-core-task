package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDateTime;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.DiscountService;

public class DiscountServiceImpl implements DiscountService {

    @Override
    public int getDiscount(User user, Event event, LocalDateTime airDateTime) {
        // TODO Auto-generated method stub
        return 0;
    }

}
