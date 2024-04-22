package tn.esprit.eventmodule.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.eventmodule.Daos.EventDao;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Event;
import tn.esprit.eventmodule.Entities.StatusType;
import tn.esprit.eventmodule.Services.EventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class EventController {
    private  final Logger LOGGER = LoggerFactory.getLogger(EventController.class);
    @Autowired
    EventImpl eventimpl;
    @Autowired
    EventDao eventDao;

    @PostMapping("/addevent")
    public Event addEvent (@RequestBody Event event) {
        //Logger.info("addEvent:{}",event);
        return eventimpl.addEvent(event) ;
    }
    @GetMapping ("/getall")
    public List<Event> getAllEvent () {
        return eventimpl.getallEvent();
    }
    @GetMapping("getAnEvent/{eventId}")
    public Event getAnEvent(@PathVariable Long eventId){return eventimpl.getAnEvent(eventId);}
    @PutMapping("/edited/{eventId}")
    public Event editedEvent (@PathVariable Long eventId, @RequestBody Event event){
        return eventimpl.editEvent(eventId, event) ;
    }
    @DeleteMapping("/deleteevent/{eventId}")
    public void deleteEvent (@PathVariable Long eventId){
        eventimpl.deleteEvent(eventId);
    }
    @GetMapping("/todaysEvents")
    public List<Event> getTodaysEvents() {
        // Get today's date
        Date today = new Date();

        // Get events with start date equals to today's date
        return eventDao.findByStartDate(today);
    }
    @GetMapping("/completedevent")
    public List<Event> completedEvent (@RequestParam StatusType status){
        return eventDao.findByStatus(status);
    }
    @GetMapping("/upcomingevents")
    public List<Event> upcomingEvents() {
        LocalDate today = LocalDate.now();
        Timestamp timestamp = java.sql.Timestamp.valueOf(today.atStartOfDay());
        return eventDao.findByStartDateAfterOrderByStartDate(timestamp);
    }

    @PostMapping("/add-participation")
    @ResponseBody
    public void addParticipation(@RequestParam String userId,  @RequestParam long eventId) {
        eventimpl.affectUserToEvent(userId, eventId);
    }
    @GetMapping("/{eventId}/users")
    public ResponseEntity<List<UserDto>> displayUsersOfEvent(@PathVariable Long eventId) {
        try {
            // Call the method to display user information for the given event ID
            List<UserDto> users = eventimpl.displayUserOfEvent(eventId);

            // Return the list of UserDto objects
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            // Handle exceptions
            // Log the error for debugging
            e.printStackTrace();

            // Return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}    /***************************************
                                              user

    @PostMapping ("/adduser")
    public User addUser (@RequestBody User user ) {
        return eventimpl.addUser(user);
    } *********************************************/
    /***************************************
                                            Participant
                                                        *****************************************/
   /* @PostMapping ("/addparticipant")
    public void addParticipantToEvent ( @RequestParam Long userID   , @RequestParam Long eventId){
        eventimpl.affectUserToEvent(userID,eventId);}*/


    /***************************************
                                            Resource

    @PostMapping("/addResource")
    public Resource addResource ( @RequestBody Resource resource){return eventimpl.addResource(resource);}
    @GetMapping("/getResource")
    public  List<Resource> getResource(){return eventimpl.getResource();}
    @PutMapping("/editResource/{resourceID}")
    public Resource editResource(@PathVariable Long resourceID,@RequestBody Resource resource){
        return eventimpl.editResource(resourceID,resource);
    }
    @DeleteMapping("/deleteResource/{resourceID}")
    public void deleteResource (@PathVariable Long resourceID){
        eventimpl.deleteResource(resourceID);
    }*****************************************/
    /***************************************
                                            Resource

    @PostMapping("/addResourcetype")
    public ResourceType addResourcetype ( @RequestBody ResourceType resourceType){return eventimpl.addResourceType(resourceType);}
    @GetMapping("/getResourcetype")
    public  List<ResourceType> getResourcetype(){return eventimpl.getResourceType();}
    @PutMapping("/editResourcetype/{id}")
    public ResourceType editResourcetype(@PathVariable Long id,@RequestBody ResourceType resourceType){
        return eventimpl.editResourceType(id,resourceType);
    }
    @DeleteMapping("/deleteResourcetype/{id}")
    public void deleteResourcetype (@PathVariable Long id){
        eventimpl.deleteResource(id);
    } *****************************************/



