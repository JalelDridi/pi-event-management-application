package tn.esprit.eventmodule.Daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationDao extends JpaRepository<Participation,Long> {

    List<Participation> findByEventId(long eventId);
    @Query("SELECT p.userID FROM Participation p WHERE p.eventId = :eventId")
    List<String> findUserIdsByEventId(@Param("eventId") Long eventId);
}
