package tn.esprit.adminmicroservice.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.adminmicroservice.Dto.EventDto;
import tn.esprit.adminmicroservice.Dto.RessourceDto;
import tn.esprit.adminmicroservice.Entities.StatusEvent;
import tn.esprit.adminmicroservice.Service.ServiceEvent;
import tn.esprit.adminmicroservice.Service.ServiceRessource;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ControllerEvent {

    @Autowired
    ServiceEvent serviceEvent;
    @GetMapping("/listEvents")
    public List<EventDto> getALLEvents(){ return serviceEvent.getALLEvents();}

    @GetMapping("/EventsConfirme")
    public List<StatusEvent> findAllconfEvents(){
        return serviceEvent.findAllconfEvents();
    }

    @PostMapping("/acceptEvent/{id]")
    public StatusEvent AcceptEvent(@PathVariable Long id){
        return serviceEvent.AcceptEvent(id);
    }
    @PostMapping("/refuserEvent/{id}")
    public StatusEvent refuserEvent(@PathVariable Long id){
        return serviceEvent.refuserEvent(id);
    }
    @GetMapping("/PourcentageEvent")
    public Double[] pourcentageEvents(){
        return serviceEvent.pourcentageEvents();
    }




}
