package tn.esprit.eventmodule.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.eventmodule.Daos.EventDao;
import tn.esprit.eventmodule.Daos.UsersEventsDao;
import tn.esprit.eventmodule.Dtos.EventAdminDto;
import tn.esprit.eventmodule.Dtos.EventReviewDto;
import tn.esprit.eventmodule.Dtos.ResourceDto;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Event;
import tn.esprit.eventmodule.Entities.EventType;
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
@RequestMapping("/Event")
public class EventController {

    @Autowired
    EventImpl eventimpl;
    @Autowired
    EventDao eventDao;
    @Autowired
    UsersEventsDao usersEventsDao;

    @PostMapping("/addevent/{userId}")
    public Event addEvent (@RequestBody Event event , @PathVariable String userId) {

        return eventimpl.addEvent(event, userId);
    }
    @GetMapping ("/getall")
    public List<Event> getAllEvent () {
        return eventimpl.getallEvent();
    }
    @GetMapping("getAnEvent/{eventId}")
    public EventAdminDto getAnEvent(@PathVariable Long eventId){return eventimpl.findEventById(eventId);}
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

//    @GetMapping("/events/byType")
//    public ResponseEntity<List<Event>> getEventsByType(@RequestParam EventType type) {
//    List<Event> events = eventDao.findByType(type);
//    return ResponseEntity.ok(events);
//}
    @GetMapping("/upcoming")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
    List<Event> events = eventimpl.getUpcomingEvents();
    return ResponseEntity.ok(events);
    }

    @PostMapping("/add-participation")
    @ResponseBody
    public void addParticipation(@RequestParam String userId,  @RequestParam long eventId) {
        eventimpl.affectUserToEvent(userId, eventId);
    }
    @GetMapping("/{eventId}/users")
    public ResponseEntity<List<UserDto>> displayUsersOfEvent(@PathVariable Long eventId) {
        try {
            List<UserDto> users = eventimpl.displayUserOfEvent(eventId);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            // Handle exceptions
            // Log the error for debugging
            e.printStackTrace();

            // Return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping("/userevents")
    public ResponseEntity<?> assignUserToEvents(@RequestParam String userId, @RequestParam Long eventIds) {

            eventimpl.assignEventToUser(userId,eventIds);
            return ResponseEntity.ok("Events assigned successfully to user with ID " + userId);

    }
    @GetMapping("/{userId}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable String userId) {
        try {
            List<Event> events = usersEventsDao.findEventsByUserId(userId);
            return ResponseEntity.ok(events);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null); // Or handle more gracefully depending on your API design
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null); // Provide a more generic error response
        }
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        try {
            UserDto user = eventimpl.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    /***************************************
                                             Resource
                                                        *************************************************/

    @PostMapping("/assign-resource")
    public ResponseEntity<String> assignResourceToEvent(@RequestParam Long resourceId, @RequestParam Long eventId) {
        try {
            eventimpl.assignResourceToEvent(resourceId, eventId);
            return ResponseEntity.ok("Resource assigned to event successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to assign resource to event.");
        }
    }
    @GetMapping("/{eventId}/resources")
    public ResponseEntity<Map<String, List<ResourceDto>>> displayResourcesOfEvent(@PathVariable Long eventId) {
        Map<String, List<ResourceDto>> resourcesByType = eventimpl.displayResourceOfEvent(eventId);
        return ResponseEntity.ok(resourcesByType);
    }

}






