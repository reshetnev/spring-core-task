package com.epam.reshetnev.spring.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.CounterDao;
import com.epam.reshetnev.spring.core.domain.Counter;
import com.epam.reshetnev.spring.core.domain.enums.CounterType;
import com.google.common.collect.Lists;

@Repository
public class CounterDaoImpl implements CounterDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Counter counter) {
         jdbcTemplate.update("INSERT INTO counters (id, counterType, keyName, valueCounter) VALUES (?,?,?,?)",
                null, counter.getCounterType().toString(), counter.getKeyName(), counter.getValueCounter());
    }

    @Override
    public void delete(Counter counter) {
        jdbcTemplate.update("DELETE FROM counters WHERE counters.id = ?", counter.getId());
    }

    @Override
    public Optional<Counter> getCounterById(Integer id) {
        List<Counter> counters = Lists.newArrayList(getAllCounters());
        Optional<Counter> counter = counters
                .stream()
                .filter(c -> (c.getId() == id))
                .findFirst();
        return counter;
    }

    @Override
    public Iterable<Counter> getAllCounters() {
        List<Counter> counters = jdbcTemplate.query("", new RowMapper<Counter>() {

            @Override
            public Counter mapRow(ResultSet rs, int rowNum) throws SQLException {
                Counter counter = new Counter(
                            rs.getInt("id"),
                            CounterType.valueOf(rs.getString("counterType")),
                            rs.getString("keyName"),
                            rs.getInt("valueCounter")
                        );
                return counter;
            }

        });

        return counters;
    }

    @Override
    public void update(Counter counter) {
        jdbcTemplate.update("UPDATE employee SET valueCounter = ?,  WHERE counters.id = ?",
                counter.getValueCounter(), counter.getId());
    }

}
