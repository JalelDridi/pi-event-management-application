package com.event.event_pi.Services;

import com.event.event_pi.Entities.Event;

import java.util.List;

public interface EventInterface {
    public Event addEvent ( Event event) ;
    public List<Event> getallEvent ();
}
