package com.epam.reshetnev.spring.core.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class User {

    private Integer id;

    private String name;

    private String email;

    private LocalDate birthDay;

    private Set<Ticket> bookedTickets = Sets.newHashSet();

    private DateTimeFormatter dateFormatter;

    public User(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Set<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(Set<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }

    public void setDateFormatter(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", birthDay=" + birthDay.format(dateFormatter)
//                + ", bookedTickets=" + bookedTickets.stream().map(t -> t.getSeat()).collect(Collectors.toList())
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
        result = prime * result + ((bookedTickets == null) ? 0 : bookedTickets.hashCode());
        result = prime * result + ((dateFormatter == null) ? 0 : dateFormatter.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        User other = (User) obj;
        if (birthDay == null) {
            if (other.birthDay != null)
                return false;
        } else if (!birthDay.equals(other.birthDay))
            return false;
        if (bookedTickets == null) {
            if (other.bookedTickets != null)
                return false;
        } else if (!bookedTickets.equals(other.bookedTickets))
            return false;
        if (dateFormatter == null) {
            if (other.dateFormatter != null)
                return false;
        } else if (!dateFormatter.equals(other.dateFormatter))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
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
        return true;
    }

}
