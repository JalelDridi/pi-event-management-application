package com.esprit.pidev.resourcemodule.controllers;

import com.esprit.pidev.resourcemodule.entities.Resource;
import com.esprit.pidev.resourcemodule.services.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
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

@PutMapping("/updateResource/{resourceTypeID}")
public ResponseEntity<Resource> updateResource(@RequestBody Resource updatedResource, @PathVariable Long resourceTypeID) {
    Resource resource = resourceService.updateResource(updatedResource, resourceTypeID);
    if (resource != null) {
        return ResponseEntity.ok(resource);
    } else {
        return ResponseEntity.notFound().build();
    }
}

 @DeleteMapping("/deleteResource/{resourceID}")
  public void deleteResource(@PathVariable Long resourceID){

      resourceService.deleteById(resourceID);
  }

  @GetMapping("/getAllAvailableResources")
    public List<Resource> getAllAvailableResources(){
      return this.resourceService.getAllAvailableResources();
    }

    @GetMapping("/findByResourceType/{resourceTypeID}")
    public ResponseEntity<List<Resource>> findResourcesByResourceType(@PathVariable Long resourceTypeID) {
        List<Resource> resources = resourceService.findResourcesByResourceType(resourceTypeID);
        return ResponseEntity.ok(resources);
    }
}
