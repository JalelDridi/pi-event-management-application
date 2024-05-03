package tn.esprit.adminmicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.adminmicroservice.Entities.StatusRessources;
import tn.esprit.adminmicroservice.Entities.StatusUser;

public interface RepositoryRessource extends JpaRepository<StatusRessources,Long> {
}
