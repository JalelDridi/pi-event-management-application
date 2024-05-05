package tn.esprit.adminmicroservice.Repository;

import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryUser extends JpaRepository<StatusUser,Long> {
    public List<StatusUser> findByFonction(String fonction );
    public StatusUser findByEmail(String email);


}
