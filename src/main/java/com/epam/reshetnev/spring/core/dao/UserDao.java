package com.epam.reshetnev.spring.core.dao;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.User;

public interface UserDao {

    public void save(User user);

    public void delete(User user);

    public User getById(Integer id);

    public List<User> getAll();

    public void update(User user);
}
