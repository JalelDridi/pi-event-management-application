package tn.esprit.eventmodule.Daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.eventmodule.Entities.ResourceReservation;

import java.util.List;

public interface ResourceReservationDao extends JpaRepository<ResourceReservation,Long> {
    @Query("SELECT p FROM ResourceReservation p WHERE p.eventId = :eventId")
    List<ResourceReservation> findByEventId(Long eventId);
}
