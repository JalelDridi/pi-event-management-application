package tn.esprit.eventmodule.Services;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.eventmodule.Dtos.EventAdminDto;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Event;

import java.util.List;
import java.util.Map;

public interface EventInterface {
    public Event addEvent(@RequestParam("file")MultipartFile file,@RequestParam("file1")MultipartFile file1, Event event, String userid);

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


    Map<String, Map<String, Double>> calculateEventPercentageByTypeAndStatus();


}
