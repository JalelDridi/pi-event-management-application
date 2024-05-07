package com.esprit.pidev.resourcemodule.daos;

import com.esprit.pidev.resourcemodule.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface ResourceDao extends JpaRepository<Resource , Long> {
    List<Resource> findByIsAvailableTrue();
    @Query("SELECT r FROM Resource r WHERE r.resourceType.resourceTypeID = ?1")
    List<Resource> findResourcesByResourceType(Long resourceTypeID);
}
