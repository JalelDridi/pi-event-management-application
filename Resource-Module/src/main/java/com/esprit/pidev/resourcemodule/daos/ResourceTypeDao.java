package com.esprit.pidev.resourcemodule.daos;

import com.esprit.pidev.resourcemodule.entities.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceTypeDao extends JpaRepository<ResourceType, Long> {
}
