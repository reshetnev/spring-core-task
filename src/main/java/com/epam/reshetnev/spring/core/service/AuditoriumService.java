package com.epam.reshetnev.spring.core.service;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.Auditorium;

public interface AuditoriumService {

    public List<Auditorium> getAll();

    public Integer getSeatsNumber(Auditorium auditorium);

    public List<Integer> getVipSeats(Auditorium auditorium);

    public Auditorium getByName(String name);
}
