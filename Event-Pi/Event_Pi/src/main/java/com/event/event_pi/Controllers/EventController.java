package com.event.event_pi.Controllers;

import com.event.event_pi.Entities.Event;
import com.event.event_pi.Entities.Participant;
import com.event.event_pi.Entities.User;
import com.event.event_pi.Services.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    EventImpl eventimpl;
    @PostMapping("/addevent")
    public Event addEvent ( @RequestBody Event event) {
        return eventimpl.addEvent(event) ;
    }
    @GetMapping ("/getall")
    public List<Event> getAllEvenet () {
        return eventimpl.getallEvent();
    }
    @PutMapping("/edited/{eventId}")
    public Event editedEvent (@PathVariable Long eventId, @RequestBody Event event){
        return eventimpl.editEvent(eventId, event) ;
    }
    @DeleteMapping("/deleteevent/{eventId}")
    public void deleteEvent (@PathVariable Long eventId){
        eventimpl.deleteEvent(eventId);
    }
    /*** user ***/
    @PostMapping ("/adduser")
    public User addUser (@RequestBody User user ) {
        return eventimpl.addUser(user);
    }
    /*** Participant ***/
    @PostMapping ("/addparticipant")
    public void addParticipantToEvent ( @RequestParam Long userID   , @RequestParam Long eventId){
        eventimpl.affectUserToEvent(userID,eventId);

    }


}
