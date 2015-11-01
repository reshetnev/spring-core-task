package com.epam.reshetnev.spring.core.domain;

import java.time.LocalDateTime;
import java.util.Set;

import com.google.common.collect.Sets;

public class Event {

    private Integer id;

    private String name;

    private LocalDateTime airDateTime;

    private Double basePrice;

    private Rating rating;

    private Auditorium auditorium;

    private Set<Ticket> tickets = Sets.newHashSet();

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

    public LocalDateTime getAirDateTime() {
        return airDateTime;
    }

    public void setAirDateTime(LocalDateTime airDateTime) {
        this.airDateTime = airDateTime;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", airDateTime=" + airDateTime + ", basePrice=" + basePrice
                + ", rating=" + rating + ", auditorium=" + auditorium
//                + ", tickets=" + tickets.stream().map(t -> t.getSeat()).collect(Collectors.toList());
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((airDateTime == null) ? 0 : airDateTime.hashCode());
        result = prime * result + ((auditorium == null) ? 0 : auditorium.hashCode());
        result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((rating == null) ? 0 : rating.hashCode());
        result = prime * result + ((tickets == null) ? 0 : tickets.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Event other = (Event) obj;
        if (airDateTime == null) {
            if (other.airDateTime != null)
                return false;
        } else if (!airDateTime.equals(other.airDateTime))
            return false;
        if (auditorium == null) {
            if (other.auditorium != null)
                return false;
        } else if (!auditorium.equals(other.auditorium))
            return false;
        if (basePrice == null) {
            if (other.basePrice != null)
                return false;
        } else if (!basePrice.equals(other.basePrice))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (rating != other.rating)
            return false;
        if (tickets == null) {
            if (other.tickets != null)
                return false;
        } else if (!tickets.equals(other.tickets))
            return false;
        return true;
    }

}
