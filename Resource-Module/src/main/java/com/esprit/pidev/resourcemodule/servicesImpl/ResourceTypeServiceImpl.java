package com.esprit.pidev.resourcemodule.servicesImpl;

import com.esprit.pidev.resourcemodule.daos.ResourceTypeDao;
import com.esprit.pidev.resourcemodule.entities.ResourceType;
import com.esprit.pidev.resourcemodule.services.ResourceTypeService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService{

    @Autowired
    private ResourceTypeDao resourceTypeDao;

    @Override
    public List<ResourceType> getAllResourceTypes() {
        return this.resourceTypeDao.findAll();
    }

    @Override
    public ResourceType addResourceType(ResourceType resourceType) {
        return this.resourceTypeDao.save(resourceType);
    }

    @Override
    public void deleteById(Long resourceTypeID) {
        this.resourceTypeDao.deleteById(resourceTypeID);

    }
    @Override
    public ResourceType updateResourceType(Long resourceTypeID, ResourceType resourceType) {
        return resourceTypeDao.findById(resourceTypeID).map(existinResource -> {
            existinResource.setResouceTypeName(resourceType.getResouceTypeName());
            return resourceTypeDao.save(existinResource);
        }).orElseThrow(() -> new ResourceNotFoundException("resource not found with id " + resourceTypeID));
    }

}
