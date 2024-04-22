package org.example.microservicenadine.Repository;

import org.example.microservicenadine.Entities.StatusUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<StatusUser,Long> {

}
