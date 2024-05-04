package com.esprit.pidev.resourcemodule.servicesImpl;

import com.esprit.pidev.resourcemodule.daos.ResourceDao;
import com.esprit.pidev.resourcemodule.daos.ResourceTypeDao;
import com.esprit.pidev.resourcemodule.entities.Resource;
import com.esprit.pidev.resourcemodule.entities.ResourceType;
import com.esprit.pidev.resourcemodule.services.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService{

    private static final Logger LOG = LoggerFactory.getLogger(ResourceServiceImpl.class);
    private static final String ERROR_NON_PRESENT_ID = "Cannot find a university with id : %s";
    private static final String ERROR_NULL_ID = "Posted ID is NULL";
    private static final String ERROR_UPDATE = "Error occured while updating";
    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private ResourceTypeDao resourceTypeDao;

    @Override
    public List<Resource> getAllResources() {
        return this.resourceDao.findAll();
    }

    @Override
    public Resource findResourceById(Long id){
       //create a variable named resource of type Resource and initializes it with null
        Resource resource = null;
        //check if the id parameter is not null
        if(id!=null){
            //This line calls the findById method of the resourceDao object to search for
            // a Resource object by its id. The result is wrapped in an Optional,
            // which may or may not contain a Resource object.
            final Optional<Resource> optionalResource = this.resourceDao.findById(id);
            if (optionalResource.isPresent()){
                resource= optionalResource.get();
            }else {
             //this line logs an informational message indicating that the id was not found in the database.
                LOG.info(String.format(ERROR_NON_PRESENT_ID,id));
                }
            } else {
            LOG.error(ERROR_NULL_ID);
        }
        return resource;
        }
    @Override
    public Resource addResource(Resource resource, Long resourceTypeID) {
        //return this.resourceDao.save(resource);
        ResourceType resourceType= resourceTypeDao.findById(resourceTypeID).get();
        //Resource savedResource=resourceDao.findById(resource.getResourceID()).get();

        resource.setResourceType(resourceType);
        return resourceDao.save(resource);
    }


//    @Override
//public Resource updateResource(Long resourceID, Resource resource , Long resourceTypeID) {
//    return resourceDao.findById(resourceID).map(existinResource -> {
//        existinResource.setResourceName(resource.getResourceName());
//        existinResource.setIsAvailable(resource.getIsAvailable());
//        existinResource.setDate(resource.getDate());
//
//        ResourceType resourceType = resourceTypeDao.findById(resourceTypeID).orElse(null);
//        existinResource.setResourceType(resourceType);
//        return resourceDao.save(existinResource);
//    }).orElseThrow(() -> new ResourceNotFoundException("resource not found with id " + resourceID));
//}

    @Override
    public Resource updateResource(Resource updatedResource, Long resourceTypeID) {
        // Récupérer la ressource existante à mettre à jour
        Resource existingResource = resourceDao.findById(updatedResource.getResourceID()).orElse(null);
        if (existingResource != null) {
            // Mettre à jour les champs de la ressource existante avec les valeurs de la ressource mise à jour
            existingResource.setResourceName(updatedResource.getResourceName());
            existingResource.setIsAvailable(updatedResource.getIsAvailable());
            existingResource.setDate(updatedResource.getDate());

            // Récupérer et définir le type de ressource
            ResourceType resourceType = resourceTypeDao.findById(resourceTypeID).orElse(null);
            if (resourceType != null) {
                existingResource.setResourceType(resourceType);
            }

            // Enregistrer la ressource mise à jour

        }
        return resourceDao.save(existingResource);
    }



    @Override
    public void deleteById(Long resourceID) {
        this.resourceDao.deleteById(resourceID);

    }

    @Override
   public List<Resource> getAllAvailableResources() {
    // Utilisation d'une requête personnalisée pour récupérer les ressources disponibles
    return resourceDao.findByIsAvailableTrue();
}

@Override
    public List<Resource> findResourcesByResourceType(Long resourceTypeID) {
        return resourceDao.findResourcesByResourceType(resourceTypeID);
    }
}


