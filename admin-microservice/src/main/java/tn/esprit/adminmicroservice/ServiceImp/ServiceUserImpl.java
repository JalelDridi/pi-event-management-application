package tn.esprit.adminmicroservice.ServiceImp;

import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;
import tn.esprit.adminmicroservice.Service.ServiceUser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import org.springframework.http.HttpMethod;
@Service
public class ServiceUserImpl implements ServiceUser {




    public void AcceptUserCnx(StatusUser statususer){
        statususer.setStatusUser(true);

        System.out.println("User Added succesfuly");
    }

    public void RefusUserCnx(StatusUser statususer){
        statususer.setStatusUser(false);
        System.out.println("User Reject");
    }

    @Override
    public List<UserDto> getALLUser() {
        // Remplacer "user-service-url" par l'URL réelle du microservice utilisateur
        // Construire l'URL sans paramètres
        String userMicroserviceUrl = "http://localhost:8091/api/v1/users";

        // Faire une requête GET au microservice utilisateur pour récupérer la liste des utilisateurs
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserDto>> responseEntity = restTemplate.exchange(
                userMicroserviceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserDto>>() {}
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
