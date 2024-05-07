package tn.esprit.eventmodule.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.eventmodule.Dtos.ResourceDto;
import tn.esprit.eventmodule.ServiceImpl.ResourceImpl;

import java.util.List;

@RestController
@RequestMapping("/Event/Resources")
public class ResourceController {
    @Autowired
    ResourceImpl resourceImpl;
    @GetMapping("/resources/by-type/{resourceTypeID}")
    public ResponseEntity<List<ResourceDto>> findResourcesByResourceType(@PathVariable Long resourceTypeID) {
        List<ResourceDto> resources = resourceImpl.findResourcesByResourceType(resourceTypeID);
        if (resources.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resources);
    }
    @GetMapping("/resources/available")
    public ResponseEntity<List<ResourceDto>> getAllAvailableResources() {
        List<ResourceDto> resources = resourceImpl.getAllAvailableResources();
        if (resources.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resources);
    }
}
