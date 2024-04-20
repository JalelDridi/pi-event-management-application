package tn.esprit.eventmodule.Daos;

import tn.esprit.eventmodule.Entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationDao extends JpaRepository<Participation,Long> {

    List<Participation> findByEventId(long eventId);
}
