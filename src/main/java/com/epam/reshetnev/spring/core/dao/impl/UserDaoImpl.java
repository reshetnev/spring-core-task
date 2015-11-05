package com.epam.reshetnev.spring.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.UserDao;
import com.epam.reshetnev.spring.core.dao.impl.rowmapper.UserRowMapper;
import com.epam.reshetnev.spring.core.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (id, name, email, birthDay) VALUES (?,?,?,?)",
                null,
                user.getName(),
                user.getEmail(),
                user.getBirthDay().toString());
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update("DELETE FROM users WHERE users.id = ?",
                user.getId());
    }

    @Override
    public User getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE users.id = ?",
                new Object[] {id},
                new UserRowMapper());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users",
                new UserRowMapper());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE users SET name = ?, email = ?, birthDay = ? WHERE users.id = ?",
                user.getName(),
                user.getEmail(),
                user.getBirthDay().toString(),
                user.getId());
    }

}
