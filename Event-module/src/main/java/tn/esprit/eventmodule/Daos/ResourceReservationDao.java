package tn.esprit.eventmodule.Daos;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.eventmodule.Entities.ResourceReservation;

import java.util.List;

public interface ResourceReservationDao extends JpaRepository<ResourceReservation,Long> {
     List<ResourceReservation> findByEventId(Long eventId);
}
