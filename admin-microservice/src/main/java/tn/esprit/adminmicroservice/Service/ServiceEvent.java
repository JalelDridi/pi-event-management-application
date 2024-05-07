package tn.esprit.adminmicroservice.Service;

import tn.esprit.adminmicroservice.Dto.EventDto;
import tn.esprit.adminmicroservice.Dto.RessourceDto;
import tn.esprit.adminmicroservice.Entities.StatusEvent;

import java.util.List;

public interface ServiceEvent {
    List<EventDto> getALLEvents();
    StatusEvent refuserEvent(Long id);
    StatusEvent AcceptEvent(Long id);
    List<StatusEvent> findAllconfEvents();
    Double[] pourcentageEvents();

}
