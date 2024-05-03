package tn.esprit.adminmicroservice.Service;

import tn.esprit.adminmicroservice.Entities.StatusRessources;

import java.util.List;

public interface ServiceRessource {

    List<StatusRessources> findAllRessource();
    double calculateAvailabilityPercentage();
}
