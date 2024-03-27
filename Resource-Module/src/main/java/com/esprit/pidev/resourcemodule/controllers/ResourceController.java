package com.esprit.pidev.resourcemodule.controllers;

import com.esprit.pidev.resourcemodule.entities.Resource;
import com.esprit.pidev.resourcemodule.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

  @Autowired
  private ResourceService resourceService;

  @GetMapping("/all-resources")
  public List<Resource> getAll(){return this.resourceService.getAllResources();}

  @GetMapping("/{id}")
  public Resource getResourceById(@PathVariable Long id){
    return id != null ? this.resourceService.findResourceById(id) : null;
  }

  @PostMapping("/addResource")
  public Resource addResource( @RequestBody Resource resource){
    return this.resourceService.addResource(resource);
  }

  @PostMapping("/updateResource")
  public Resource updateResource(@RequestBody Resource resource){
    return resource != null ? this.resourceService.updateResource(resource) : null;
  }

 @DeleteMapping("/deleteResource/{resourceID}")
  public void deleteResource(@PathVariable Long resourceID){

      resourceService.deleteById(resourceID);
  }

}
