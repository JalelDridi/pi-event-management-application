package com.esprit.pidev.resourcemodule.servicesImpl;

import com.esprit.pidev.resourcemodule.daos.ResourceTypeDao;
import com.esprit.pidev.resourcemodule.entities.ResourceType;
import com.esprit.pidev.resourcemodule.services.ResourceTypeService;
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
}
