package com.event.event_pi.Daos;

import com.event.event_pi.Entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceDao extends JpaRepository<Resource,Long> {
}
