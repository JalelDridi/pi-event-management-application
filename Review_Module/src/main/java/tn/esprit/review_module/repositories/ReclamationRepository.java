package tn.esprit.review_module.repositories;

import tn.esprit.review_module.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

    List<Reclamation> getReclamationByEventId(Long eventId);

    List<Reclamation> getReclamationsByUserId(String userId);

    List<Reclamation> findReclamationsByUserIdAndEventId(String userid,Long eventid);

//List<Reclamation> findReclamationsByDateReclamationBefore(LocalDate date);


}