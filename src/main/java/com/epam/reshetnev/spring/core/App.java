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

import com.epam.reshetnev.spring.core.aspect.CounterAspect;
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

    private List<Integer> seats1;

    private List<Integer> seats2;

    public List<Integer> getSeats1() {
        return seats1;
    }

    public void setSeats1(List<Integer> seats1) {
        this.seats1 = seats1;
    }

    public List<Integer> getSeats2() {
        return seats2;
    }

    public void setSeats2(List<Integer> seats2) {
        this.seats2 = seats2;
    }

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
        app.initUsers(ctx);
        app.initEvents(ctx);
        CounterAspect counterAspect = ctx.getBean(CounterAspect.class);

        Event event1 = app.getEventByName("MINIONS");
        app.initTickets(ctx, event1, app.seats1);

        Event event2 = app.getEventByName("007 Spectr");
        app.initTickets(ctx, event2, app.seats2);

        User user1 = app.getUserByEmail("kim1@gmail.com");
        User user2 = app.getUserByEmail("kim2@gmail.com");

        List<Ticket> tickets1 = app.getTicketsByEvent(event1);
        List<Ticket> tickets2 = app.getTicketsByEvent(event2);
        
        app.bookTickets(user1, tickets1);
        app.bookTickets(user2, tickets2);

        app.printAllAuditoriums();

        app.printAllUsers();

        app.printAllEvents();

        app.printAllTickets();

        app.printTicketPrices(event1, user1, app.seats1);
        app.printTicketPrices(event2, user2, app.seats2);

        app.printCounterAspect(counterAspect);

        ctx.close();
    }

    private void printCounterAspect(CounterAspect counterAspect) {
        log.info(counterAspect.getCounterGetEventByName().toString());
    }

    private List<Ticket> getTicketsByEvent(Event event) {
        List<Ticket> tickets = Lists.newArrayList();
        Ticket ticket = getTicketByEvent(event);
        tickets.add(ticket);
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

    private void bookTicket(User user, Ticket ticket) {
        bookingService.bookTicket(user, ticket);
    }

    private void bookTickets(User user, List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            bookTicket(user, ticket);
        }
    }

    private User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    private Event getEventByName(String name) {
        return eventService.getByName(name);
    }

    private Ticket getTicketByEvent(Event event) {
        return ticketService.getByEvent(event);
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

    private Ticket createTicket(ConfigurableApplicationContext ctx, Event event, Integer seat) {
        Ticket ticket= ctx.getBean(Ticket.class);

        ticket.setEvent(event);
        ticket.setSeat(seat);

        return ticket;
    }

//    private List<Ticket> createTicketList(ConfigurableApplicationContext ctx, List<Integer> seats) {
//        List<Ticket> tickets = Lists.newArrayList();
//        for (Integer seat : seats) {
//            Ticket ticket = createTicket(ctx, seat);
//            tickets.add(ticket);
//            ticketService.register(ticket);
//        }
//
//        return tickets;
//    }

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

    private void initEvents(ConfigurableApplicationContext ctx) {
        Event event1 = createEvent(ctx, "MINIONS", "25.10.2015 15.00", 200d, Rating.HIGH,
                getAuditoriumByName("Big"));
        eventService.create(event1);

        Event event2 = createEvent(ctx, "007 Spectr", "30.10.2015 21.00", 100d, Rating.MID,
                getAuditoriumByName("Mid"));
        eventService.create(event2);
    }

    private void initTickets(ConfigurableApplicationContext ctx, Event event, List<Integer> seats) {
        List<Ticket> tickets = Lists.newArrayList();
        for (Integer seat : seats) {
            Ticket ticket = createTicket(ctx, event, seat);
            tickets.add(ticket);
            ticketService.register(ticket);
        }
    }

}
