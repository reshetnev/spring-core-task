package com.epam.reshetnev.spring.core.dao.impl;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.UserDao;
import com.epam.reshetnev.spring.core.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ConcurrentMap<String, User> users = new ConcurrentHashMap<String, User>();

    @Override
    public User save(User user) {
        while (true) {
            Integer id = generateId();
            user.setId(id);
            if (users.putIfAbsent(id.toString(), user) == null) {
                break;
            } else {
                continue;
            }
        }
        return user;
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId().toString());
    }

    @Override
    public User getUserById(Integer id) {
        return users.get(id);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return users.values();
    }

//    @Override
//    public User update(User user) {
//        return users.put(user.getId().toString(), user);
//    }

    public static Integer generateId() {
        Random r = new Random();
        return r.ints(1, Integer.MAX_VALUE).limit(1).findFirst().getAsInt();
    }
}
