package com.epam.reshetnev.spring.core.service;

import com.epam.reshetnev.spring.core.domain.Auditorium;

public interface AuditoriumService {

    public Iterable<Auditorium> getAuditoriums();

    public Integer getSeatsNumber(Auditorium auditorium);

    public Iterable<Integer> getVipSeats(Auditorium auditorium);
}
