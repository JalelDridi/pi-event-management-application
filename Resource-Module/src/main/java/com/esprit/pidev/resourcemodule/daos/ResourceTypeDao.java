package com.esprit.pidev.resourcemodule.daos;

import com.esprit.pidev.resourcemodule.entities.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface ResourceTypeDao extends JpaRepository<ResourceType, Long> {
    @Query("SELECT rt.resourceTypeName, COUNT(r) FROM ResourceType rt JOIN rt.resources r GROUP BY rt.resourceTypeName")
    List<Object[]> getResourceTypeCounts();
    //  List<Object[]> getResourceTypeCounts();
}
