package com.esprit.pidev.resourcemodule.daos;

import com.esprit.pidev.resourcemodule.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface ResourceDao extends JpaRepository<Resource , Long> {
    List<Resource> findByIsAvailableTrue();
}
