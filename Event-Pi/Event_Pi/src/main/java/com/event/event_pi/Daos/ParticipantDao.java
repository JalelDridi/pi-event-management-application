package com.event.event_pi.Daos;

import com.event.event_pi.Entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantDao extends JpaRepository<Participant,Long> {
}
