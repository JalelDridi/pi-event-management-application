package tn.esprit.eventmodule.Services;

import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Event;
import tn.esprit.eventmodule.Entities.Participation;
import com.event.eventmodule.Entities.*;

import java.util.List;

public interface EventInterface {
    public Event addEvent (Event event) ;
    public List<Event> getallEvent ();
    public Event getAnEvent(Long eventId);
    public Long getEventId();
    public Event editEvent( Long eventId , Event event) ;
    public void deleteEvent (Long eventId)    ;
    public void updateEventStatusAutomatiquement();
    /********************************** User *************************************/
    //public User addUser (User user );
    public List<UserDto> findByEvent(Long eventId);
    public void affectUserToEvent(String userID, long eventId);
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
