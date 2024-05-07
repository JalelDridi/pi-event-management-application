package tn.esprit.adminmicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.adminmicroservice.Entities.StatusEvent;
import tn.esprit.adminmicroservice.Entities.StatusUser;

public interface RepositoryEvent  extends JpaRepository<StatusEvent,Long> {
}
