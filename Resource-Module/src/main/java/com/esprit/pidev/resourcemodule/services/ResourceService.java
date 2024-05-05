package com.esprit.pidev.resourcemodule.services;

import com.esprit.pidev.resourcemodule.entities.Resource;

import java.util.List;

public interface ResourceService {

    List<Resource> getAllResources();

    Resource findResourceById(Long id);

    Resource addResource(Resource resource, Long resourceTypeID);

 //   Resource updateResource(Long resourceID, Resource resource , Long resourceTypeID);

    void deleteById(Long resourceID);

    List<Resource> getAllAvailableResources();

    Resource updateResource(Resource updatedResource, Long resourceTypeID);

    List<Resource> findResourcesByResourceType(Long resourceTypeID);

//    Resource findById(Long id);
}
