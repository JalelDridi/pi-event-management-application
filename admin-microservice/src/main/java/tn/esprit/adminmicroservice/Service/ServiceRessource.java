package tn.esprit.adminmicroservice.Service;

import tn.esprit.adminmicroservice.Dto.RessourceDto;

import java.util.List;

public interface ServiceRessource {

    double calculateAvailabilityPercentage();
    List<RessourceDto> getALLRessources();
}
