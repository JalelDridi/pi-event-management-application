package com.event.event_pi.Services;

import com.event.event_pi.Daos.EventDao;
import com.event.event_pi.Entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventImpl implements EventInterface {
    @Autowired
    EventDao eventDao;
    @Override
    public Event addEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public List<Event> getallEvent() {
        return eventDao.findAll();
    }
}
