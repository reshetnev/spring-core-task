package com.epam.reshetnev.spring.core.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class Auditorium {

    private String name;

    private Integer numberOfSeats;

    private List<Integer> vipSeats = Lists.newArrayList();

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

    public List<Integer> getVipSeats() {
        return vipSeats;
    }

    @Override
    public String toString() {
        return "Auditorium [name=" + name + ", numberOfSeats=" + numberOfSeats + ", vipSeats=" + vipSeats + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((numberOfSeats == null) ? 0 : numberOfSeats.hashCode());
        result = prime * result + ((vipSeats == null) ? 0 : vipSeats.hashCode());
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
        Auditorium other = (Auditorium) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (numberOfSeats == null) {
            if (other.numberOfSeats != null)
                return false;
        } else if (!numberOfSeats.equals(other.numberOfSeats))
            return false;
        if (vipSeats == null) {
            if (other.vipSeats != null)
                return false;
        } else if (!vipSeats.equals(other.vipSeats))
            return false;
        return true;
    }

}
