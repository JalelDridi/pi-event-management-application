package tn.esprit.adminmicroservice.ServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.adminmicroservice.Dto.EventDto;
import tn.esprit.adminmicroservice.Dto.RessourceDto;
import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Ennum.Role;
import tn.esprit.adminmicroservice.Entities.StatusEvent;
import tn.esprit.adminmicroservice.Entities.StatusUser;
import tn.esprit.adminmicroservice.Service.ServiceRessource;

import java.util.List;

@Service
public class ServiceRessourceImpl implements ServiceRessource {



    @Override
    public double calculateAvailabilityPercentage() {
        List<RessourceDto> allRessources = getALLRessources();
        int totalQuantity = 0;
        int totalReservations = 0;
        for (RessourceDto ressource : allRessources) {
            totalQuantity += ressource.getQuantite();
            totalReservations += ressource.getNbreReserve();
        }
        double availabilityPercentage = ((double)(totalQuantity - totalReservations) / totalQuantity) * 100;
        return availabilityPercentage;
    }

    @Override
    public List<RessourceDto> getALLRessources() {
        // Remplacer "user-service-url" par l'URL réelle du microservice utilisateur
        // Construire l'URL sans paramètres
        String ressourceMicroserviceUrl = "http://resourceservice/api/resources/all-resources";

        // Faire une requête GET au microservice utilisateur pour récupérer la liste des utilisateurs
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<RessourceDto>> responseEntity = restTemplate.exchange(
                ressourceMicroserviceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RessourceDto>>() {}
        );
        // Vérifier si la requête a réussi (code de statut HTTP 200)
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody(); // Retourner la liste des utilisateurs
        } else {
            // Gérer le cas où la requête n'a pas réussi
            // Vous pouvez lancer une exception ou le gérer en fonction des besoins de votre application
            throw new RuntimeException("Failed to fetch users from User Microservice. Status code: " + responseEntity.getStatusCodeValue());
        }
    }





}
