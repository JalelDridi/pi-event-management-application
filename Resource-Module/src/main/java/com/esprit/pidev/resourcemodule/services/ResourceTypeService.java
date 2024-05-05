package com.esprit.pidev.resourcemodule.services;

import com.esprit.pidev.resourcemodule.entities.ResourceType;

import java.util.List;

public interface ResourceTypeService {
    List<ResourceType> getAllResourceTypes();

    ResourceType findResourceTypeById(Long resourceTypeID);

    ResourceType addResourceType(ResourceType resourceType);

    void deleteById(Long resourceTypeID);

    ResourceType updateResourceType(Long resourceTypeID, ResourceType resourceType);

    ResourceType getResourceTypeById(Long resourceTypeID);
}
