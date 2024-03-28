package com.event.event_pi.Controllers;

import com.event.event_pi.Entities.Event;
import com.event.event_pi.Services.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    EventImpl eventimpl;
    @PostMapping("/add")
    public Event addEvent ( @RequestBody Event event) {
        return eventimpl.addEvent(event) ;
    }
    @GetMapping ("/getall")
    public List<Event> getAllEvenet () {
        return eventimpl.getallEvent();
    }
}
