package com.epam.reshetnev.spring.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.reshetnev.spring.core.domain.Auditorium;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Rating;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.AuditoriumService;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.epam.reshetnev.spring.core.service.UserService;
import com.google.common.collect.Lists;

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

        List<Integer> seats = Lists.newArrayList(2,3,4,5,6,7,8,9,10,11);

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        app.initUsers(ctx);
        app.initEvent(ctx);
        app.initTicket(ctx, seats);

        Event event = app.getEventByName("MINIONS");

        User user1 = app.getUserByEmail("kim1@gmail.com");

        List<Ticket> tickets = app.getTicketsBySeats(seats);

        app.setEventToTickets(event, tickets);

        app.bookTickets(user1, tickets);

        app.printAllAuditoriums();

        app.printAllUsers();

        app.printAllEvents();

        app.printAllTickets();

        app.printTicketPrices(event, user1, seats);

        ctx.close();
    }

    private void setEventToTickets(Event event, List<Ticket> tickets) {
        for (Ticket ticket :tickets) {
            setEventToTicket(event, ticket);
        }
    }

    private List<Ticket> getTicketsBySeats(List<Integer> seats) {
        List<Ticket> tickets = Lists.newArrayList();
        for (Integer seat : seats) {
            Ticket ticket = getTicketBySeat(seat);
            tickets.add(ticket);
        }
        return tickets;
    }

    private void printTicketPrices(Event event, User user, List<Integer> seats) {

        List<Double> prices = bookingService.getTicketPrices(event, event.getAirDateTime(), seats, user);
        log.info(prices.toString());
    }

    private void printAllAuditoriums() {
        auditoriumService.getAuditoriums().forEach(a -> log.info(a.toString()));
    }

    private void printAllUsers() {
        userService.getAllUsers().forEach(u -> log.info(u.toString()));
    }

    private void printAllEvents() {
        eventService.getAll().forEach(e -> log.info(e.toString()));
    }

    private void printAllTickets() {
        ticketService.getAllTickets().forEach(t -> log.info(t.toString()));
    }

    private void setEventToTicket(Event event, Ticket ticket) {
        ticket.setEvent(event);
    }

    private void bookTicket(User user, Ticket ticket) {
        bookingService.bookTicket(user, ticket);
    }

    private void bookTickets(User user, List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            bookingService.bookTicket(user, ticket);
        }
    }

    private User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    private Event getEventByName(String name) {
        return eventService.getByName(name);
    }

    private Ticket getTicketBySeat(Integer seat) {
        return ticketService.getBySeat(seat);
    }

    private Auditorium getAuditoriumByName(String name) {
        return auditoriumService.getAuditoriumByName(name);
    }

    private User registerUser(User user) {
        return userService.register(user);
    }

    private Event createEvent(ConfigurableApplicationContext ctx, String name, String airDateTime, Double basePrice,
            Rating rating, Auditorium auditorium) {

        Event event = ctx.getBean(Event.class);

        event.setName(name);
        event.setBasePrice(basePrice);
        event.setRating(rating);

        eventService.assignAuditorium(event, auditorium, LocalDateTime.parse(airDateTime, dateTimeFormatter));

        return event;
    }

    private User createUser(ConfigurableApplicationContext ctx, String userName, String userEmail,
            String userBirthDay) {
        User user = (User) ctx.getBean("user");

        user.setName(userName);
        user.setEmail(userEmail);
        user.setBirthDay(LocalDate.parse(userBirthDay, dateFormatter));

        return user;
    }

    private Ticket createTicket(ConfigurableApplicationContext ctx, Integer seat) {
        Ticket ticket= ctx.getBean(Ticket.class);

        ticket.setSeat(seat);

        return ticket;
    }

    private List<Ticket> createTicketList(ConfigurableApplicationContext ctx, List<Integer> seats) {
        List<Ticket> tickets = Lists.newArrayList();
        for (Integer seat : seats) {
            Ticket ticket= ctx.getBean(Ticket.class);

            ticket.setSeat(seat);
            tickets.add(ticket);
        }

        return tickets;
    }

    private void initUsers(ConfigurableApplicationContext ctx) {
        User user1 = createUser(ctx, usersProps.getProperty("kim1.name"), usersProps.getProperty("kim1.email"),
                usersProps.getProperty("kim1.birthDay"));
        registerUser(user1);

        User user2 = createUser(ctx, usersProps.getProperty("kim2.name"), usersProps.getProperty("kim2.email"),
                usersProps.getProperty("kim2.birthDay"));
        registerUser(user2);

        User user3 = createUser(ctx, usersProps.getProperty("ivan.name"), usersProps.getProperty("ivan.email"),
                usersProps.getProperty("ivan.birthDay"));
        registerUser(user3);
    }

    private void initEvent(ConfigurableApplicationContext ctx) {
        Event event = createEvent(ctx, "MINIONS", "25.10.2015 15.00", 100d, Rating.HIGH,
                getAuditoriumByName("Big"));
        eventService.create(event);
    }

    private void initTicket(ConfigurableApplicationContext ctx, List<Integer> seats) {

        List<Ticket> tickets = createTicketList(ctx, seats);

        for (Ticket ticket : tickets) {
            ticketService.register(ticket);
        }

    }

}
