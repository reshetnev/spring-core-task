package com.epam.reshetnev.spring.core;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.reshetnev.spring.core.aspect.CounterAspect;
import com.epam.reshetnev.spring.core.aspect.DiscountAspect;
import com.epam.reshetnev.spring.core.domain.Counter;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.domain.enums.CounterType;
import com.epam.reshetnev.spring.core.domain.enums.Rating;
import com.epam.reshetnev.spring.core.service.AuditoriumService;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.CounterService;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.epam.reshetnev.spring.core.service.UserService;
import com.google.common.collect.Lists;

public class App {

    private static final Logger log = Logger.getLogger(App.class);

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

    @Autowired
    private CounterService counterService;

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        CounterAspect counterAspect = ctx.getBean(CounterAspect.class);
        DiscountAspect discountAspect = ctx.getBean(DiscountAspect.class);

        User user1 = app.getUserByEmail("kim1@gmail.com");
        Event event1 = app.getEventByName("MINIONS");
        List<Integer> seatsForUser1 = Lists.newArrayList(2,3,4,5,6,7,8,9,10,11);
        app.printTicketPrices(event1, user1, seatsForUser1);
        app.initTickets(ctx, event1, seatsForUser1);
        List<Ticket> tickets1 = app.getTicketsByEventAndSeats(event1, seatsForUser1);
        app.bookTickets(user1, tickets1);

        User user2 = app.getUserByEmail("kim2@gmail.com");
        Event event21 = app.getEventByName("007 Spectr");
        List<Integer> seatsForUser2 = Lists.newArrayList(5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27);
        app.printTicketPrices(event21, user2, seatsForUser2);
        app.initTickets(ctx, event21, seatsForUser2);
        List<Ticket> tickets2 = app.getTicketsByEventAndSeats(event21, seatsForUser2);
        app.bookTickets(user2, tickets2);

        User user4 = ctx.getBean(User.class);
        Event event22 = app.getEventByName("007 Spectr");
        List<Integer> seatsForUser4 = Lists.newArrayList(1,2,30,31);
        app.printTicketPrices(event22, user4, seatsForUser4);
        app.initTickets(ctx, event22, seatsForUser4);
        List<Ticket> tickets4 = app.getTicketsByEventAndSeats(event22, seatsForUser4);
        app.bookTickets(user4, tickets4);

        app.createEvent(ctx, "Everest", "2015-12-01", "20:00", 10d, "low", "Small");
        User user3 = app.getUserByEmail("iivanov@gmail.com");
        Event event31 = app.getEventByName("Everest");
        Event event23 = app.getEventByName("007 Spectr");
        List<Integer> seatsForUser3 = Lists.newArrayList(3,4);
        app.printTicketPrices(event31, user3, seatsForUser3);
        app.printTicketPrices(event23, user3, seatsForUser3);
        app.initTickets(ctx, event31, seatsForUser3);
        app.initTickets(ctx, event23, seatsForUser3);

        app.createUser(ctx, "Alex", "alex@gmail.com", "1985-10-30");
        User user5 = app.getUserByEmail("alex@gmail.com");
        Event event32 = app.getEventByName("Everest");
        List<Integer> seatsForUser5 = Lists.newArrayList(17,18);
        app.printTicketPrices(event32, user5, seatsForUser5);
        app.initTickets(ctx, event32, seatsForUser5);

        app.printAllAuditoriums();

        app.printAllUsers();

        app.printAllEvents();

        app.printAllTickets();

        app.printCounterAspect(counterAspect);
        app.printDiscountAspect(discountAspect);

//        ctx.close();
    }

    private void printDiscountAspect(DiscountAspect discountAspect) {

        Map<String, Integer> counterTotal = discountAspect.getCounter();

        Map<String, Integer> counterBirthDayGetDiscountForUser = discountAspect.getCounterBirthDayGetDiscountForUser();

        Map<String, Integer> counterEveryTenGetDiscountForUser = discountAspect.getCounterEveryTenGetDiscountForUser();

        log.info("Total discounts: " + counterTotal.toString());
        log.info("BirthDay Discount: " + counterBirthDayGetDiscountForUser.toString());
        log.info("EveryTenGetDiscount: " + counterEveryTenGetDiscountForUser.toString());

        for (String key : counterTotal.keySet()) {
            counterService.save(new Counter(CounterType.DISCOUNT_TOTAL, key, counterTotal.get(key)));
        }

        for (String key : counterBirthDayGetDiscountForUser.keySet()) {
            counterService.save(new Counter(CounterType.DISCOUNT_BIRTH_DAY, key, counterBirthDayGetDiscountForUser.get(key)));
        }

        for (String key : counterEveryTenGetDiscountForUser.keySet()) {
            counterService.save(new Counter(CounterType.DISCOUNT_EVERY_TEN, key, counterEveryTenGetDiscountForUser.get(key)));
        }
    }

    private void printCounterAspect(CounterAspect counterAspect) {

        Map<String, Integer> counterGetEventByName = counterAspect.getCounterGetEventByName();

        Map<String, Integer> counterGetTicketPrices = counterAspect.getCounterGetTicketPrices();

        Map<String, Integer> counterBookTicket = counterAspect.getCounterBookTicket();

        log.info("Event was get by name: "+ counterGetEventByName.toString());
        log.info("Prices of Event were queried: "+ counterGetTicketPrices.toString());
        log.info("Tickets were booked: "+ counterBookTicket.toString());

        for (String key : counterGetEventByName.keySet()) {
            counterService.save(new Counter(CounterType.GET_EVENT_BY_NAME, key, counterGetEventByName.get(key)));
        }

        for (String key : counterGetTicketPrices.keySet()) {
            counterService.save(new Counter(CounterType.GET_TICKET_PRICES, key, counterGetTicketPrices.get(key)));
        }

        for (String key : counterBookTicket.keySet()) {
            counterService.save(new Counter(CounterType.BOOK_TICKET, key, counterBookTicket.get(key)));
        }

    }

    private List<Ticket> getTicketsByEventAndSeats(Event event, List<Integer> seats) {
        return ticketService.getByEvent(event, seats);
    }

    private void printTicketPrices(Event event, User user, List<Integer> seats) {

        List<Double> prices = bookingService.getTicketPrices(event, event.getDate(), seats, user);
        log.info(prices.toString());
    }

    private void printAllAuditoriums() {
        auditoriumService.getAuditoriums().forEach(a -> log.info(a.toString()));
    }

    private void printAllUsers() {
        userService.getAll().forEach(u -> log.info(u.toString()));
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
        return userService.getByEmail(email);
    }

    private Event getEventByName(String name) {
        return eventService.getByName(name);
    }

    private Ticket createTicket(ConfigurableApplicationContext ctx, Event event, Integer seat) {
        Ticket ticket= ctx.getBean(Ticket.class);

        ticket.setEvent(event);
        ticket.setSeat(seat);

        return ticket;
    }

    private void initTickets(ConfigurableApplicationContext ctx, Event event, List<Integer> seats) {
        for (Integer seat : seats) {
            Ticket ticket = createTicket(ctx, event, seat);
            ticketService.register(ticket);
        }
    }

    private void createEvent(ConfigurableApplicationContext ctx, String name, String date, String time,
            Double basePrice, String rating, String auditorium) {

        Event event = ctx.getBean(Event.class);

        event.setName(name);
        event.setBasePrice(basePrice);
        event.setRating(Rating.valueOf(rating.toUpperCase()));

        eventService.assignAuditorium(event, auditorium, date, time);

        eventService.save(event);
    }

    private void createUser(ConfigurableApplicationContext ctx, String userName, String userEmail,
            String userBirthDay) {

        User user = ctx.getBean(User.class);

        user.setName(userName);
        user.setEmail(userEmail);
        user.setBirthDay(LocalDate.parse(userBirthDay));

        userService.save(user);
    }

}
