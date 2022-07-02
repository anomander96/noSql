package com.anomander.noSql.task1.repository;

import com.anomander.noSql.task1.model.Event;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EventRepository extends MongoRepository<Event, Long> {

    List<Event> findAllByTitle(String title);

    @NonNull
    List<Event> findAll();
}
