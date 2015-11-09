package com.epam.reshetnev.spring.core.service;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;

public interface UserService {

    public void save(User user);

    public void delete(User user);

    public User getById(Integer id);

    public User getByEmail(String email);

    public List<User> getAllByName(String name);

    public List<Ticket> getBookedTickets(User user);

    public List<User> getAll();

    public void update(User user);
}
