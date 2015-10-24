package com.epam.reshetnev.spring.core.domain;

import java.time.LocalDateTime;
import java.util.Set;

public class Event {

    private Integer id;

    private String name;

    private Set<LocalDateTime> airDatesTimes;

    private Integer basePrice;

    private Rating rating;

    private Auditorium auditorium;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LocalDateTime> getAirDatesTimes() {
        return airDatesTimes;
    }

    public void setAirDatesTimes(Set<LocalDateTime> airDatesTimes) {
        this.airDatesTimes = airDatesTimes;
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

}
