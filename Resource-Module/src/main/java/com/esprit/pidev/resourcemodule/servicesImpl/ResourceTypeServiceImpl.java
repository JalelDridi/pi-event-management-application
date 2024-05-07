package com.esprit.pidev.resourcemodule.servicesImpl;

import com.esprit.pidev.resourcemodule.daos.ResourceTypeDao;
import com.esprit.pidev.resourcemodule.entities.ResourceType;
import com.esprit.pidev.resourcemodule.services.ResourceTypeService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceTypeServiceImpl implements ResourceTypeService{

    @Autowired
    private ResourceTypeDao resourceTypeDao;
    private static final Logger LOG = LoggerFactory.getLogger(ResourceServiceImpl.class);
    private static final String ERROR_NON_PRESENT_ID = "Cannot find a university with id : %s";
    private static final String ERROR_NULL_ID = "Posted ID is NULL";

    @Override
    public List<ResourceType> getAllResourceTypes() {
        return this.resourceTypeDao.findAll();
    }


    @Override
    public ResourceType findResourceTypeById(Long resourceTypeID){
        //create a variable named resource of type Resource and initializes it with null
        ResourceType resourceType = null;
        //check if the id parameter is not null
        if(resourceTypeID!=null){
            //This line calls the findById method of the resourceDao object to search for
            // a Resource object by its id. The result is wrapped in an Optional,
            // which may or may not contain a Resource object.
            final Optional<ResourceType> optionalResourceType = this.resourceTypeDao.findById(resourceTypeID);
            if (optionalResourceType.isPresent()){
                resourceType= optionalResourceType.get();
            }else {
                //this line logs an informational message indicating that the id was not found in the database.
                LOG.info(String.format(ERROR_NON_PRESENT_ID,resourceTypeID));
            }
        } else {
            LOG.error(ERROR_NULL_ID);
        }
        return resourceType;
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
            existinResource.setResourceTypeName(resourceType.getResourceTypeName());
            return resourceTypeDao.save(existinResource);
        }).orElseThrow(() -> new ResourceNotFoundException("resource not found with id " + resourceTypeID));
    }

    @Override
    public ResourceType getResourceTypeById(Long resourceTypeID) {
        return null;
    }

}
