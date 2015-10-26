package com.epam.reshetnev.spring.core.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.domain.Auditorium;
import com.epam.reshetnev.spring.core.service.AuditoriumService;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private List<Auditorium> auditoriumList;
    
    public List<Auditorium> getAuditoriums() {
        return auditoriumList;
    }

    @Override
    public Integer getSeatsNumber(Auditorium auditorium) {
        return auditorium.getNumberOfSeats();
    }

    @Override
    public List<Integer> getVipSeats(Auditorium auditorium) {
        return auditorium.getVipSeats();
    }
}
