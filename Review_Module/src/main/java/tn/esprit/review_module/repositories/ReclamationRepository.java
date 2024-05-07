package tn.esprit.review_module.repositories;

import tn.esprit.review_module.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.review_module.entities.TypeReclamation;

import java.time.LocalDate;
import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
    Reclamation findReclamationByIdRec(Long id);

    List<Reclamation> getReclamationByEventId(Long eventId);

    List<Reclamation> getReclamationsByUserId(String userId);

    List<Reclamation> findReclamationByUserIdAndEventId( String userId, Long eventId);
    ////////////////:Long countByTypeRec(TypeReclamation typerec);



//List<Reclamation> findReclamationsByDateReclamationBefore(LocalDate date);
    


}