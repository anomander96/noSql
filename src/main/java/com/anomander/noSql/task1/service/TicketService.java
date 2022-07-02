package com.anomander.noSql.task1.service;

import com.anomander.noSql.task1.model.Category;
import com.anomander.noSql.task1.model.Event;
import com.anomander.noSql.task1.model.Ticket;
import com.anomander.noSql.task1.model.User;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(long userId, long eventId, int place, Category category);

    List<Ticket> getBookedTickets(User user);

    List<Ticket> getBookedTickets(Event event);

    void cancelTicket(long ticketId);

    List<Ticket> getAllTickets();
}
