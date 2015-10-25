package com.epam.reshetnev.spring.core.domain;

import java.time.LocalDate;
import java.util.Set;

import com.google.common.collect.Sets;

public class User {

    private Integer id;

    private String name;

    private String email;

    private LocalDate birthDay;

    private Set<Ticket> bookedTickets = Sets.newHashSet();

    public User(String name, String email
//            , LocalDate birthDay
            ) {
        this.name = name;
        this.email = email;
//        this.birthDay = birthDay;
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

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", birthDay=" + birthDay + ", bookedTickets="
                + bookedTickets + "]";
    }

}
