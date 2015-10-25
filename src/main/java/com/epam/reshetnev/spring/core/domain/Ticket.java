package com.epam.reshetnev.spring.core.domain;

import java.time.LocalDateTime;
import java.util.Set;

public class Ticket {

    private Integer id;

    private Event event;

    private LocalDateTime airDateTime;
    
    private Set<Integer> bookedSeats;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getAirDateTime() {
        return airDateTime;
    }

    public void setAirDateTime(LocalDateTime airDateTime) {
        this.airDateTime = airDateTime;
    }

    public Set<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(Set<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
