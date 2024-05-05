package tn.esprit.eventmodule.Services;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.eventmodule.Dtos.EventAdminDto;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Event;

import java.util.List;
import java.util.Map;

public interface EventInterface {
    public Event addEvent(@RequestParam("file") MultipartFile file, Event event, String userid) ;

        List<Event> getallEvent ();
    EventAdminDto findEventById(Long eventId);
    Event editEvent( Long eventId , Event event) ;
    void deleteEvent (Long eventId)    ;
    void updateEventStatusAutomatiquement();
    /********************************** User *************************************/
    //public User addUser (User user );
    UserDto getUserById(String userId);
    void affectUserToEvent(String userID, long eventId);
    public void assignEventToUser(String userId, Long eventId);
//    void sendEmail(String toEmail, String subject, String body);
//    void envoyerEmailParticipant(Participation participant, Event event) ;

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
     *                                              ************************************/
    //public void displayReclamationsForEvent(long eventId);
    /****************************************
     *                                  Statistiques
     *                                              ************************************/
    Map<String, Map<String, Double>> calculateEventPercentageByTypeAndStatus();


}
