package com.anomander.noSql.task1.repository;

import com.anomander.noSql.task1.model.Ticket;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, Long> {

    List<Ticket> findAllByUserId(long userId);

    List<Ticket> findAllByEventId(long eventId);

    @NonNull
    List<Ticket> findAll();
}
