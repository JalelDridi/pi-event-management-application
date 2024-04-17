package com.event.event_pi.Controllers;

import com.event.event_pi.Entities.*;
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
    /***************************************
                                              user
                                                    *********************************************/
    @PostMapping ("/adduser")
    public User addUser (@RequestBody User user ) {
        return eventimpl.addUser(user);
    }
    /***************************************
                                            Participant
                                                        *****************************************/
    @PostMapping ("/addparticipant")
    public void addParticipantToEvent ( @RequestParam Long userID   , @RequestParam Long eventId){
        eventimpl.affectUserToEvent(userID,eventId);

    }
    /***************************************
                                            Resource
                                                    *****************************************/
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
    }
    /***************************************
                                            Resource
                                                     *****************************************/
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
    }



}
