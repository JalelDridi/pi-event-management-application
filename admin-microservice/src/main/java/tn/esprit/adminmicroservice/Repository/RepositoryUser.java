package tn.esprit.adminmicroservice.Repository;

import tn.esprit.adminmicroservice.Entities.StatusUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<StatusUser,Long> {

}
