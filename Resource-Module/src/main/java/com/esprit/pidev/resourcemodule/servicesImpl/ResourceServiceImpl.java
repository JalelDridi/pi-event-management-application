package com.esprit.pidev.resourcemodule.servicesImpl;

import com.esprit.pidev.resourcemodule.daos.ResourceDao;
import com.esprit.pidev.resourcemodule.entities.Resource;
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
    @Autowired
    private ResourceDao resourceDao;

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
        }

