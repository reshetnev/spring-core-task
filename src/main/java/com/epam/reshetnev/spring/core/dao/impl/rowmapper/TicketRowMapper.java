package com.epam.reshetnev.spring.core.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.reshetnev.spring.core.domain.Ticket;

public class TicketRowMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ticket(rs.getInt("id"),
                rs.getInt("eventId"),
                rs.getInt("userId") != 0 ? rs.getInt("userId") : null,
                rs.getInt("seat"),
                rs.getBoolean("isPurchased"));
    }

}
