package com.esprit.pidev.resourcemodule.controllers;

import com.esprit.pidev.resourcemodule.entities.Resource;
import com.esprit.pidev.resourcemodule.services.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin
public class ResourceController {

  @Autowired
  private ResourceService resourceService;

  @GetMapping("/all-resources")
  public List<Resource> getAll(){return this.resourceService.getAllResources();}

  @GetMapping("/{id}")
  public Resource getResourceById(@PathVariable Long id){
    return id != null ? this.resourceService.findResourceById(id) : null;
  }

  @PostMapping("/addResource/{resourceTypeID}")
  public Resource addResource( @RequestBody Resource resource, @PathVariable Long resourceTypeID){
    return this.resourceService.addResource(resource,resourceTypeID);
  }

  @PutMapping("/updateResource/{resourceID}")
  public Resource updateResource(@PathVariable Long resourceID ,@RequestBody Resource resource){
    return resource != null ? this.resourceService.updateResource(resourceID,resource) : null;
  }

 @DeleteMapping("/deleteResource/{resourceID}")
  public void deleteResource(@PathVariable Long resourceID){

      resourceService.deleteById(resourceID);
  }

  @GetMapping("/getAllAvailableResources")
    public List<Resource> getAllAvailableResources(){
      return this.resourceService.getAllAvailableResources();
    }

//    @GetMapping("/recherche/{date}")
//    public List<Resource>findResourcesByAvailabilityAndDate(@PathVariable Date date){
//      return this.searchService.findResourcesByAvailabilityAndDate(date);
//    }
}
