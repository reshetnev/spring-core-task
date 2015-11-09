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
import com.google.common.base.Preconditions;

@Service
public class CounterServiceImpl implements CounterService {

    private static final Logger log = Logger.getLogger(CounterServiceImpl.class);

    @Autowired
    private CounterDao counterDao;

    @Override
    public void save(Counter counter) {
        counterDao.save(counter);
    }

    @Override
    public void delete(Counter counter) {
        counterDao.delete(counter);
    }

    @Override
    public Counter getById(Integer id) {
        return counterDao.getById(id);
    }

    @Override
    public List<Counter> getAll() {
        return counterDao.getAll();
    }

    @Override
    public Counter getByTypeAndKeyName(CounterType counterType, String keyName) {
        Optional<Counter> counter = getAll()
                .stream()
                .filter(c -> ((c.getCounterType() == counterType) &&
                        (c.getKeyName().equals(keyName))))
                .findFirst();

        if (!counter.isPresent()) {
            log.info("Counter is not found with (CounterType, KeyName): " + counterType + ", " + keyName);
            return null;
        }

        return counter.get();
    }

    @Override
    public void update(Counter counter) {
        Preconditions.checkNotNull(counter.getId(), "Counter id should not be null");
        counterDao.update(counter);
    }

}
