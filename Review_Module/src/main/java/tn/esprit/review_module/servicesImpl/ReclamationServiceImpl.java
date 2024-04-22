package tn.esprit.review_module.servicesImpl;

import tn.esprit.review_module.dtos.EventDto;
import tn.esprit.review_module.entities.Reclamation;
import tn.esprit.review_module.repositories.EventRepository;
import tn.esprit.review_module.repositories.ReclamationRepository;
import tn.esprit.review_module.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReclamationServiceImpl implements ReclamationService {


    private final ReclamationRepository reclamationRepository;
    private final EventRepository eventRepository;

    @Autowired
    public ReclamationServiceImpl(ReclamationRepository reclamationRepository, EventRepository eventRepository ) {
        this.reclamationRepository = reclamationRepository;
                this.eventRepository = eventRepository;


    }
    List<Reclamation> reclamations;
    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }


    @Override
    public List<Reclamation> findByeventId(Long eventId) {

        return reclamationRepository.getReclamationByEventId(eventId);
    }

    @Override
    public List<Reclamation> findByUserId(Long userId) {
        return reclamationRepository.getReclamationByUserId(userId);
    }


}
