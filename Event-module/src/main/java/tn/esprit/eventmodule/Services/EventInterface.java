package tn.esprit.eventmodule.Services;

import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Event;
import tn.esprit.eventmodule.Entities.Participation;

import java.util.List;
import java.util.Map;

public interface EventInterface {
    Event addEvent (Event event) ;
    List<Event> getallEvent ();
    Event getAnEvent(Long eventId);
    Long getEventId();
    Event editEvent( Long eventId , Event event) ;
    void deleteEvent (Long eventId)    ;
    void updateEventStatusAutomatiquement();
    /********************************** User *************************************/
    //public User addUser (User user );
    void affectUserToEvent(String userID, long eventId);
//    void sendEmail(String toEmail, String subject, String body);
//    void envoyerEmailParticipant(Participation participant, Event event) ;

   // Map<String, Map<String, Double>> calculateEventPercentageByTypeAndStatus();
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
    /****************************************
     *                                  Reclamation
     *                                              ******************************/////////
    //public void displayReclamationsForEvent(long eventId);


}
