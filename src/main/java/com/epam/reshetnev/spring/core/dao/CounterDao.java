package com.epam.reshetnev.spring.core.dao;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.Counter;

public interface CounterDao {

    public void save(Counter counter);

    public void delete(Counter counter);

    public Counter getById(Integer id);

    public List<Counter> getAll();

    public void update(Counter counter);
}
