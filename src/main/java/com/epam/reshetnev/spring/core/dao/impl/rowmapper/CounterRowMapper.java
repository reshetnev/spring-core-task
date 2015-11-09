package com.epam.reshetnev.spring.core.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.reshetnev.spring.core.domain.Counter;
import com.epam.reshetnev.spring.core.domain.enums.CounterType;

public class CounterRowMapper implements RowMapper<Counter> {

    @Override
    public Counter mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Counter(rs.getInt("id"),
                CounterType.valueOf(rs.getString("counterType")),
                rs.getString("keyName"),
                rs.getInt("valueCounter"));
    }

}
