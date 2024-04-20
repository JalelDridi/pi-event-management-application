package com.event.event_pi.Daos;

import com.event.event_pi.Entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationDao extends JpaRepository<Participation,Long> {

    List<Participation> findByEventId(long eventId);
}
