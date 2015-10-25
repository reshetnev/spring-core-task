package com.epam.reshetnev.spring.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.UserDao;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.UserService;
import com.google.common.collect.Lists;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User register(User user) {
        return userDao.save(user);
    }

    @Override
    public void remove(User user) {
        userDao.delete(user);

    }

    @Override
    public User getById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        User userByEmail = null;

        for (User user : getAllUsers()) {
            if (user.getEmail().equals(email)) {
                userByEmail = user;
            }
        }

        return userByEmail;

    }

    @Override
    public List<User> getUsersByName(String name) {
        List<User> users = Lists.newArrayList();


        for (User user : getAllUsers()) {
            if (user.getName().equals(name)) {
                users.add(user);
            }
        }

        return users;
    }

    @Override
    public Iterable<Ticket> getBookedTickets(User user) {
        return user.getBookedTickets();
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userDao.getAllUsers();
    }

}
