package com.event.event_pi.Services;

import com.event.event_pi.Dtos.UserDto;
import com.event.event_pi.Entities.*;

import java.util.List;

public interface EventInterface {
    public Event addEvent ( Event event) ;
    public List<Event> getallEvent ();
    public Event getAnEvent(Long eventId);
    public Long getEventId();
    public Event editEvent( Long eventId , Event event) ;
    public void deleteEvent (Long eventId)    ;
    /********************************** User *************************************/
    //public User addUser (User user );
    public List<UserDto> findByEvent(Long eventId);
    public void affectUserToEvent (String userID , Long eventId);
    public void sendEmail(String toEmail, String subject, String body);
    public void envoyerEmailParticipant(Participation participant, Event event) ;
    /********************************** Resource
    public Resource addResource (Resource resource);
    public List<Resource> getResource();
        public Resource editResource (Long resourceID , Resource resource);
    public void deleteResource ( Long resourceID);
     *************************************/
    /************************************* ResourceType
    public ResourceType addResourceType (ResourceType resourceType);
    public List<ResourceType> getResourceType();
    public ResourceType editResourceType (Long id , ResourceType resourceType);
    public void deleteResourceType ( Long id);
     ****************************************/

}