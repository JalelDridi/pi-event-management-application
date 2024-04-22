package tn.esprit.eventmodule.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.eventmodule.Daos.EventDao;
import tn.esprit.eventmodule.Dtos.ResourceDto;
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
import java.util.Map;

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
    /***************************************
                                             Resource
                                                        *************************************************/
    @PostMapping("/{eventId}/resources/{resourceId}")
    public ResponseEntity<String> assignResourceToEvent(@PathVariable Long eventId, @PathVariable Long resourceId) {
        eventimpl.assignResourceToEvent(resourceId, eventId);
        return ResponseEntity.ok("Resource assigned to event successfully.");
    }

    @GetMapping("/{eventId}/resources")
    public ResponseEntity<Map<String, List<ResourceDto>>> displayResourcesOfEvent(@PathVariable Long eventId) {
        Map<String, List<ResourceDto>> resourcesByType = eventimpl.displayResourcesOfEvent(eventId);
        return ResponseEntity.ok(resourcesByType);
    }

}






