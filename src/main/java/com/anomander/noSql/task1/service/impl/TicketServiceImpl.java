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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository myTicketRepository;

    @Autowired
    UserService userService;

    @Autowired
    EventService eventService;

    @Autowired
    UserAccountService userAccountService;

    @Override
    public Ticket bookTicket(long userId, long eventId, int place, Category category) {
        log.info("booking ticket");

        userService.getUserById(userId);
        Event eventById = eventService.getById(eventId);

        log.info("withdrawing money from account for event " + eventById);
        userAccountService.withdrawMoneyFromAccount(userId, eventById.getTicketPrice());

        Ticket ticket = new Ticket(eventId, userId, category, place);
        return myTicketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        log.info("getBookedTickets by user " + user);
        return myTicketRepository.findAllByUserId(user.getId());
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        log.info("getBookedTickets by event " + event);
        return myTicketRepository.findAllByEventId(event.getId());
    }

    @Override
    public void cancelTicket(long ticketId) {
        log.info("deleting ticket by id " + ticketId);
        myTicketRepository.deleteById(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return myTicketRepository.findAll();
    }

}
