package com.esprit.pidev.resourcemodule.daos;

import com.esprit.pidev.resourcemodule.entities.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ResourceTypeDao extends JpaRepository<ResourceType, Long> {
}
