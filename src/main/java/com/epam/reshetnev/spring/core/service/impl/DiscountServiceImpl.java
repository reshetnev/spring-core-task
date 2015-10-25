package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.discount.DiscountStrategy;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private List<DiscountStrategy> discountStrategies;

    @Override
    public Double getDiscount(User user, Event event, LocalDate airDate) {

        Double discount = 0d;

        for (DiscountStrategy strategy : discountStrategies) {
            discount += strategy.getDiscount(user, event, airDate);
        }

        return discount;
    }

}
