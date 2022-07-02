package com.anomander.noSql.task1.service.impl;

import com.anomander.noSql.task1.model.Category;
import com.anomander.noSql.task1.model.Event;
import com.anomander.noSql.task1.model.Ticket;
import com.anomander.noSql.task1.model.User;
import com.anomander.noSql.task1.repository.TicketRepository;
import com.anomander.noSql.task1.service.EventService;
import com.anomander.noSql.task1.service.TicketService;
import com.anomander.noSql.task1.service.UserAccountService;
import com.anomander.noSql.task1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final EventService eventService;
    private final UserAccountService userAccountService;

    public TicketServiceImpl(TicketRepository ticketRepository, UserService userService, EventService eventService, UserAccountService userAccountService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.eventService = eventService;
        this.userAccountService = userAccountService;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        log.info("booking ticket");

        userService.getUserById(userId);
        Event eventById = eventService.getById(eventId);

        log.info("withdrawing money from account for event " + eventById);
        userAccountService.withdrawMoneyFromAccount(userId, eventById.getTicketPrice());

        Ticket ticket = new Ticket(eventId, userId, category, place);
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        log.info("getBookedTickets by user " + user);
        return ticketRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        log.info("getBookedTickets by event " + event);
        return ticketRepository.findAllByEventId(event.getId());
    }

    @Override
    public void cancelTicket(long ticketId) {
        log.info("deleting ticket by id " + ticketId);
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

}
