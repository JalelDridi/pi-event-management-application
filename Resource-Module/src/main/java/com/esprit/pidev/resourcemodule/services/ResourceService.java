package com.esprit.pidev.resourcemodule.services;

import com.esprit.pidev.resourcemodule.entities.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> getAllResources();

    Resource findResourceById(Long id);

    Resource addResource(Resource resource);

    Resource updateResource(Resource resource);

    void deleteById(Long resourceID);

    List<Resource> getAllAvailableResources();

//    Resource findById(Long id);
}
