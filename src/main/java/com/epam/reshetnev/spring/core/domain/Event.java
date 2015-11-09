package com.epam.reshetnev.spring.core.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import com.epam.reshetnev.spring.core.domain.enums.Rating;

public class Event {

    private Integer id;

    private String name;

    private LocalDate date;

    private LocalTime time;

    private Double basePrice;

    private Rating rating;

    private String auditorium;

    public Event() {
    }

    public Event(Integer id, String name, LocalDate date, LocalTime time, Double basePrice, Rating rating,
            String auditorium) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.basePrice = basePrice;
        this.rating = rating;
        this.auditorium = auditorium;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(String auditorium) {
        this.auditorium = auditorium;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", date=" + date + ", time=" + time + ", basePrice=" + basePrice
                + ", rating=" + rating + ", auditorium=" + auditorium + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auditorium == null) ? 0 : auditorium.hashCode());
        result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((rating == null) ? 0 : rating.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
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
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
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
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

}
