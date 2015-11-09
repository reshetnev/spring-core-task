package com.epam.reshetnev.spring.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.domain.Auditorium;
import com.epam.reshetnev.spring.core.service.AuditoriumService;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private static final Logger log = Logger.getLogger(AuditoriumServiceImpl.class);

    @Autowired
    private List<Auditorium> auditoriumList;

    public List<Auditorium> getAll() {
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

    @Override
    public Auditorium getByName(String name) {
        Optional<Auditorium> auditorium = getAll()
                .stream()
                .filter(a -> (a.getName().equals(name)))
                .findFirst();

        if (!auditorium.isPresent()) {
            log.info("Auditorium is not found with (name): " + name);
            return null;
        }

        return auditorium.get();
    }
}
