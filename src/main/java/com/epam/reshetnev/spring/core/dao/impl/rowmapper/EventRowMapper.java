package com.epam.reshetnev.spring.core.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.jdbc.core.RowMapper;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.enums.Rating;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Event(rs.getInt("id"),
                rs.getString("name"),
                LocalDate.parse(rs.getString("airDate")),
                LocalTime.parse(rs.getString("airTime")),
                rs.getDouble("basePrice"),
                Rating.valueOf(rs.getString("rating")),
                rs.getString("auditorium"));
    }

}
