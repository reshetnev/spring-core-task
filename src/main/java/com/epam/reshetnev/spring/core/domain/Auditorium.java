package com.epam.reshetnev.spring.core.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class Auditorium {

    private String name;

    private Integer numberOfSeats;

    private Set<Integer> vipSeats = Sets.newLinkedHashSet();

    public Auditorium(String name, Integer numberOfSeats, String vipSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;

        List<String> strList = Arrays.asList(vipSeats.split(","));
        List<Integer> intList = strList.stream().map(Integer::valueOf).collect(Collectors.toList());
        this.vipSeats.addAll(intList);
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public Set<Integer> getVipSeats() {
        return vipSeats;
    }

    @Override
    public String toString() {
        return "Auditorium [name=" + name + ", numberOfSeats=" + numberOfSeats + ", vipSeats=" + vipSeats + "]";
    }

}
