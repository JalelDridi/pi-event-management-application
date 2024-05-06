package tn.esprit.review_module.servicesImpl;
import com.mysql.cj.protocol.MessageSender;
import org.springframework.mail.SimpleMailMessage;
import tn.esprit.review_module.entities.TypeReclamation;
import tn.esprit.review_module.entities.Reclamation;
import tn.esprit.review_module.repositories.ReclamationRepository;
import tn.esprit.review_module.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.List;
@Service
public class ReclamationServiceImpl implements ReclamationService {


    private final ReclamationRepository reclamationRepository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public ReclamationServiceImpl(ReclamationRepository reclamationRepository,  JavaMailSender javaMailSender ) {
        this.reclamationRepository = reclamationRepository;
        this.javaMailSender = javaMailSender;


    }
    List<Reclamation> reclamations;
    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        if (reclamation.getTypeRec() == TypeReclamation.EVENT) {
            reclamation.setEventId(reclamation.getEventId());
            reclamation.setResourceId(null);
        } else if (reclamation.getTypeRec() == TypeReclamation.RESOURCE) {
                    reclamation.setEventId(null);
                    reclamation.setResourceId(reclamation.getResourceId());
        } else if (reclamation.getTypeRec()== TypeReclamation.SITE) {
            reclamation.setResourceId(null);
            reclamation.setEventId(null);
        }
        return reclamationRepository.save(reclamation);
    }


    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    @Override
    public void SendReclamationEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reclamation Submitted Successfully");
        message.setText("Your reclamation has been submitted successfully.");
        javaMailSender.send(message);

    }


    @Override
    public List<Reclamation> findByeventId(Long eventId) {
        return reclamationRepository.getReclamationByEventId(eventId);
    }

    @Override
    public List<Reclamation> findByUserId(String userId) {
        return reclamationRepository.getReclamationsByUserId(userId);
    }

    @Override
    public List<Reclamation> findreclamationbyeventidanduserid(String userId, Long eventId) {
        return reclamationRepository.findReclamationByUserIdAndEventId(userId, eventId);
    }

    @Override
    public void deletereclamation(Long IdRec) {
        Reclamation rec = reclamationRepository.findReclamationByIdRec(IdRec);
        if (rec != null){
           reclamationRepository.delete(rec);
        }
    }
}