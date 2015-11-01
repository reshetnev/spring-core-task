package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.discount.DiscountStrategy;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

    private static final Logger log = Logger.getLogger(DiscountServiceImpl.class);

    @Autowired
    private List<DiscountStrategy> discountStrategies;

    private Boolean isBirthDay = false;

    private Boolean isEveryTen = false;

    @Override
    public Double getDiscount(User user, Event event, LocalDate airDate) {

        Double discount = 0d;

        if (isBirthDay) {
            discount += discountStrategies.get(0).getDiscount(user, event, airDate);
            log.info("User has BirthDay Discount!");
        }

        if (isEveryTen) {
            discount += discountStrategies.get(1).getDiscount(user, event, airDate);
            log.info("User has EveryTen Discount!");
        }

        return discount;
    }

    @Override
    public void checkDiscounts(User user, Event event, LocalDate airDate, Integer ordinalNumberTicket) {

        isBirthDay = false;
        //set isBirthDay
        if ((user.getId() != null) && (user.getBirthDay() != null)) {
            if ((user.getBirthDay().getDayOfMonth() == airDate.getDayOfMonth()) &&
                (user.getBirthDay().getMonthValue() == airDate.getMonthValue())) {
                isBirthDay = true;
            }
        }

        isEveryTen = false;
        //set isEveryTen
        if ((ordinalNumberTicket % 10) == 0) {
            isEveryTen = true;
        }

    }

}
