package com.epam.reshetnev.spring.core.dao;

import java.util.Optional;

import com.epam.reshetnev.spring.core.domain.Counter;

public interface CounterDao {

    public void save(Counter counter);

    public void delete(Counter counter);

    public Optional<Counter> getCounterById(Integer id);

    public Iterable<Counter> getAllCounters();

    public void update(Counter counter);
}
