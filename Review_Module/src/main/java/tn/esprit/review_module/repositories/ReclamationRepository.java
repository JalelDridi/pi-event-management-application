package tn.esprit.review_module.repositories;

import tn.esprit.review_module.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
}
