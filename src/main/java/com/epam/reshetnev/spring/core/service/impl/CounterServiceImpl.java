package com.epam.reshetnev.spring.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.CounterDao;
import com.epam.reshetnev.spring.core.domain.Counter;
import com.epam.reshetnev.spring.core.domain.enums.CounterType;
import com.epam.reshetnev.spring.core.service.CounterService;
import com.google.common.collect.Lists;

@Service
public class CounterServiceImpl implements CounterService {

    private static final Logger log = Logger.getLogger(CounterServiceImpl.class);

    @Autowired
    private CounterDao counterDao;

    @Override
    public void insert(Counter counter) {
        counterDao.save(counter);
    }

    @Override
    public void delete(Counter counter) {
        counterDao.delete(counter);
    }

    @Override
    public Counter getById(Integer id) {

        Optional<Counter> counterOpt = counterDao.getCounterById(id);

        if (!counterOpt.isPresent()) {
            log.info("Counter is not found with id = " + id);
        }

        return counterOpt.get();
    }

    @Override
    public List<Counter> getAll() {
        return Lists.newArrayList(counterDao.getAllCounters());
    }

    @Override
    public Optional<Counter> getByTypeAndKeyName(CounterType counterType, String keyName) {
        Optional<Counter> counter = getAll()
                .stream()
                .filter(c -> ((c.getCounterType() == counterType) &&
                        (c.getKeyName() == keyName)))
                .findFirst();
        return counter;
    }

    @Override
    public void update(Counter counter) {

        Optional<Counter> counterOpt = getByTypeAndKeyName(counter.getCounterType(), counter.getKeyName());

        if (counterOpt.isPresent()) {
            counterDao.update(counter);
        } else {
            log.info("Counter is not found with (CounterType, KeyName): " + counter.getCounterType() + counter.getKeyName());
        }
    }

}
