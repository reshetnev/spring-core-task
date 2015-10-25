package com.epam.reshetnev.spring.core.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.UserDao;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.util.Generator;
import com.google.common.collect.Maps;

@Repository
public class UserDaoImpl implements UserDao {

    private Map<String, User> users = Maps.newHashMap();

    @Override
    public User save(User user) {
        while (true) {
            Integer id = Generator.generateId();
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

}
