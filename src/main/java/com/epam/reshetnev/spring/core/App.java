package com.epam.reshetnev.spring.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.UserService;

public class App {

    @Autowired
    private List<User> userList;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Iterable<User> users = app.userService.registerAll(app.userList);
        for (User user : users) {
            System.out.println(user.toString());
        }

        ctx.close();
    }

}
