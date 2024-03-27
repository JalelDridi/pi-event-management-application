package com.esprit.pidev.resourcemodule.daos;

import com.esprit.pidev.resourcemodule.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceDao extends JpaRepository<Resource , Long> {
}
