package com.esprit.pidev.resourcemodule.services;

import com.esprit.pidev.resourcemodule.entities.ResourceType;

import java.util.List;

public interface ResourceTypeService {
    List<ResourceType> getAllResourceTypes();

    ResourceType addResourceType(ResourceType resourceType);
}
