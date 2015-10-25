package com.epam.reshetnev.spring.core.dao;

import com.epam.reshetnev.spring.core.domain.User;

public interface UserDao {

    public User save(User user);

    public void delete(User user);

    public User getUserById(Integer id);

    public Iterable<User> getAllUsers();

//    public User update(User user);
}
