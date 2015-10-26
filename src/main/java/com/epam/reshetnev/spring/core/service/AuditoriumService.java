package com.epam.reshetnev.spring.core.service;

import java.util.List;
import java.util.Set;

import com.epam.reshetnev.spring.core.domain.Auditorium;

public interface AuditoriumService {

    public List<Auditorium> getAuditoriums();

    public Integer getSeatsNumber(Auditorium auditorium);

    public List<Integer> getVipSeats(Auditorium auditorium);
}
