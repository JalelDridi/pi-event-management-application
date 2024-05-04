package com.esprit.pidev.resourcemodule.controllers;


import com.esprit.pidev.resourcemodule.entities.ResourceType;
import com.esprit.pidev.resourcemodule.services.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ResourceTypes")
@CrossOrigin
public class ResourceTypeController {


    @Autowired
    private ResourceTypeService resourceTypeService;

    @GetMapping("/allResourceTypes")
    public List<ResourceType> getAllResourceTypes(){
        return this.resourceTypeService.getAllResourceTypes();
    }

    @PostMapping("/addResourceType")
    public ResourceType addResourceType(ResourceType resourceType){
        return this.resourceTypeService.addResourceType(resourceType);
    }
    @PutMapping("/updateResourceType/{resourceTypeID}")
    public ResourceType updateResourceType(@PathVariable Long resourceTypeID , @RequestBody ResourceType resourceType){
        return resourceType != null ? this.resourceTypeService.updateResourceType(resourceTypeID,resourceType) : null;
    }
    @DeleteMapping("/deleteResourceType/{resourceTypeID}")
    public void deleteResource(@PathVariable Long resourceTypeID){

        resourceTypeService.deleteById(resourceTypeID);
    }

}
