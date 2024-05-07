package com.esprit.pidev.resourcemodule.controllers;

import com.esprit.pidev.resourcemodule.daos.ResourceTypeDao;
//import com.esprit.pidev.resourcemodule.dto.StatisticDto;
import com.esprit.pidev.resourcemodule.dto.StatisticDto;
import com.esprit.pidev.resourcemodule.entities.Resource;
import com.esprit.pidev.resourcemodule.services.ResourceService;

//import com.esprit.pidev.resourcemodule.servicesImpl.StatisticServiceImpl;
import com.esprit.pidev.resourcemodule.servicesImpl.StatisticServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin
public class ResourceController {

  @Autowired
  private ResourceService resourceService;


  @Autowired
  private ResourceTypeDao resourceTypeDao;

  @Autowired
  private StatisticServiceImpl statisticService;

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
    @GetMapping("/statistics/resource-types")
    public ResponseEntity<List<StatisticDto>> getResourceTypeStatistics() {
        List<StatisticDto> statistics = statisticService.getResourceTypeStatistics();
        return ResponseEntity.ok(statistics);
    }
//    @GetMapping("/search")
//    public List<Resource> searchResourcesByResourceName(@RequestParam String resourceName) {
//        return resourceService.searchResourcesByResourceName(resourceName);
//    }
//@GetMapping("/search")
//public ResponseEntity<List<Resource>> searchResources(@RequestParam String query) {
//    List<Resource> searchResults = resourceService.searchResourcesByResourceName(query);
//    return ResponseEntity.ok().body(searchResults);
//}

    @GetMapping("/search")
    public ResponseEntity<List<Resource>> searchResources(@RequestParam String query) {
      //The query parameter is expected to contain the search term (resource name) that the user inputs in the frontend.
        // This parameter is then passed to the searchResourcesByResourceName method of the resourceService
        // , which performs the actual search operation based on the resource name.
        List<Resource> searchResults = resourceService.searchResourcesByResourceName(query);
        if (searchResults.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(searchResults);
    }
}
