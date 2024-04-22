package tn.esprit.review_module.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.review_module.dtos.EventDto;
import tn.esprit.review_module.entities.Reclamation;
import tn.esprit.review_module.repositories.EventRepository;
import tn.esprit.review_module.repositories.ReclamationRepository;
import tn.esprit.review_module.services.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository= eventRepository;
    }

    @Override
    public EventDto getEventDtoById(Long eventId) {
        return eventRepository.findEventDtoById(eventId);
    }
}
