package com.event.event_pi.Daos;

import com.event.event_pi.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDao extends JpaRepository<Event, Long> {
}
