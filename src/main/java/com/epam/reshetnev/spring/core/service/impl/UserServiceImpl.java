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
    public Iterable<User> registerAll(Iterable<User> users) {
        List<User> registeredUsers = Lists.newArrayList();
        for (User user : users) {
            registeredUsers.add(register(user));
        }
        return registeredUsers;

    }

    @Override
    public void remove(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void getUserByEmail(String email) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterable<User> getUsersByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Ticket> getBookedTickets(User user) {
        // TODO Auto-generated method stub
        return null;
    }
}
