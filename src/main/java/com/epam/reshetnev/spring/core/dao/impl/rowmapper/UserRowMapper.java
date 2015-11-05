package com.epam.reshetnev.spring.core.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.epam.reshetnev.spring.core.domain.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                LocalDate.parse(rs.getString("birthDay")));
    }

}
