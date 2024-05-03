package tn.esprit.adminmicroservice.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.adminmicroservice.Entities.StatusRessources;
import tn.esprit.adminmicroservice.Service.ServiceRessource;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ControllerRessource {

    @Autowired
    ServiceRessource serviceRessource;


    @GetMapping("/listeRessources")
    public List<StatusRessources> FindAllRessource(){
        return serviceRessource.findAllRessource();
    }

    @GetMapping("/pourcentageRessource")
    public double calculateAvailabilityPercentage(){
        return serviceRessource.calculateAvailabilityPercentage();
    }


}
