package com.event.event_pi.Services;

import com.event.event_pi.Entities.Event;
import com.event.event_pi.Entities.Participant;
import com.event.event_pi.Entities.User;

import java.util.List;

public interface EventInterface {
    public Event addEvent ( Event event) ;
    public List<Event> getallEvent ();
    public Event editEvent( Long eventId , Event event) ;
    public void deleteEvent (Long eventId)    ;
    /*** User */
    public User addUser (User user );
    public void affectUserToEvent (Long userID , Long eventId);
    public void sendEmail(String toEmail, String subject, String body);
    public void envoyerEmailParticipant(Participant participant, Event event) ;


    }
