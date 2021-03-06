package com.anomander.noSql.task1.service.impl;

import com.anomander.noSql.task1.exception.EventNotFoundException;
import com.anomander.noSql.task1.repository.EventRepository;
import com.anomander.noSql.task1.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anomander.noSql.task1.model.Event;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository myEventRepository;

    @Override
    public Event getById(long id) {
        log.info("getting event by id " + id);

        return myEventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found by id " + id));
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        log.info("getting events by title " + title);
        return myEventRepository.findAllByTitle(title);
    }

    @Override
    public List<Event> getEventsForDay(Date day) {
        log.info("getting event by day " + day);
        int dayOfWeekFromDate = getDayOfWeekFromDate(day);

        return myEventRepository.findAll()
                .stream()
                .filter(event -> getDayOfWeekFromDate(event.getDate()) == dayOfWeekFromDate)
                .sorted(Comparator.comparing(Event::getId))
                .collect(Collectors.toList());
    }

    private int getDayOfWeekFromDate(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    @Override
    public Event createEvent(Event event) {
        log.info("creating event:  " + event);
        return myEventRepository.save(event);
    }

    @Transactional
    @Override
    public Event updateEvent(Event event) {
        log.info("updating event " + event);

        return getEventById(event.getId())
                .setDate(event.getDate())
                .setTitle(event.getTitle());
    }

    private Event getEventById(long eventId) {
        return myEventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("such event id " + eventId + " doesn't exists"));
    }

    @Override
    public void deleteEvent(long eventId) {
        log.info("deleting event by id " + eventId);
        myEventRepository.deleteById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return myEventRepository.findAll();
    }
}
