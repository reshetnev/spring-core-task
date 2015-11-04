package com.epam.reshetnev.spring.core.service;

import java.util.List;
import java.util.Optional;

import com.epam.reshetnev.spring.core.domain.Counter;
import com.epam.reshetnev.spring.core.domain.enums.CounterType;

public interface CounterService {

    public void insert(Counter counter);

    public void delete(Counter counter);

    public Counter getById(Integer id);

    public Optional<Counter> getByTypeAndKeyName(CounterType counterType, String keyName);

    public List<Counter> getAll();

    public void update(Counter counter);
}
