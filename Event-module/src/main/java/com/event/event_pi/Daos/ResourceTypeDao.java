package com.event.event_pi.Daos;

import com.event.event_pi.Entities.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceTypeDao extends JpaRepository<ResourceType,Long> {
}
