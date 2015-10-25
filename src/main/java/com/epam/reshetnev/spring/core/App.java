package com.epam.reshetnev.spring.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.AuditoriumService;
import com.epam.reshetnev.spring.core.service.UserService;

public class App {

    @Autowired
    @Qualifier("usersProps")
    private Properties usersProps;

    @Autowired
    @Qualifier("dateFormatter")
    private DateTimeFormatter dateFormatter;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditoriumService auditoriumService;

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        User user1 = (User) ctx.getBean("user");
        user1.setName(app.usersProps.getProperty("kim1.name"));
        user1.setEmail(app.usersProps.getProperty("kim1.email"));
        user1.setBirthDay(LocalDate.parse(app.usersProps.getProperty("kim1.birthDay"), app.dateFormatter));
        app.userService.register(user1);
        
        User user2 = (User) ctx.getBean("user");
        user2.setName(app.usersProps.getProperty("kim2.name"));
        user2.setEmail(app.usersProps.getProperty("kim2.email"));
        user2.setBirthDay(LocalDate.parse(app.usersProps.getProperty("kim2.birthDay"), app.dateFormatter));
        app.userService.register(user2);
        
        User user3 = (User) ctx.getBean("user");
        user3.setName(app.usersProps.getProperty("ivan.name"));
        user3.setEmail(app.usersProps.getProperty("ivan.email"));
        user3.setBirthDay(LocalDate.parse(app.usersProps.getProperty("ivan.birthDay"), app.dateFormatter));
        app.userService.register(user3);
        
        app.userService.getAllUsers().forEach(user -> System.out.println(user.toString()));
        
        app.auditoriumService.getAuditoriums().forEach(auditorium -> System.out.println(auditorium.toString()));

        ctx.close();
    }

}
