package com.epam.reshetnev.spring.core.service;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;

public interface UserService {

    public User register(User user);

    public void remove(User user);

    public User getById(Integer id);

    public User getUserByEmail(String email);

    public Iterable<User> getUsersByName(String name);

    public Iterable<Ticket> getBookedTickets(User user);
    
    public Iterable<User> getAllUsers();
}
