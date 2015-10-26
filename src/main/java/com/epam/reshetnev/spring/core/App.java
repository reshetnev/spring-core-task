package com.epam.reshetnev.spring.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Rating;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.AuditoriumService;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.epam.reshetnev.spring.core.service.UserService;

public class App {

    private static final Logger log = Logger.getLogger(App.class);

    @Autowired
    @Qualifier("usersProps")
    private Properties usersProps;

    @Autowired
    @Qualifier("dateFormatter")
    private DateTimeFormatter dateFormatter;

    @Autowired
    @Qualifier("dateTimeFormatter")
    private DateTimeFormatter dateTimeFormatter;

    @Autowired
    @Qualifier("timeFormatter")
    private DateTimeFormatter timeFormatter;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private TicketService ticketService;

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

        //not registered
        User user4 = (User) ctx.getBean("user");
        user4.setName("Aleks");
        user4.setEmail("aleks@gmail.com");
        user4.setBirthDay(LocalDate.parse("04.04.1984", app.dateFormatter));

        Event event = ctx.getBean(Event.class);
        event.setName("MINIONS");
        event.setBasePrice(100d);
        event.setRating(Rating.HIGH);
        app.eventService.create(event);
        app.eventService.assignAuditorium(event,
                app.auditoriumService.getAuditoriums().get(0),
                LocalDateTime.parse("25.10.2015 18.00", app.dateTimeFormatter));

        Ticket ticket= ctx.getBean(Ticket.class);
        ticket.setEvent(event);
        ticket.setUser(user1);
        ticket.setAirDateTime(event.getAirDateTime());
        app.ticketService.register(ticket);

        app.userService.getAllUsers().forEach(user -> log.info(user.toString()));

        app.auditoriumService.getAuditoriums().forEach(auditorium -> log.info(auditorium.toString()));

        app.eventService.getAll().forEach(e -> log.info(event.toString()));

        ctx.close();
    }

}
