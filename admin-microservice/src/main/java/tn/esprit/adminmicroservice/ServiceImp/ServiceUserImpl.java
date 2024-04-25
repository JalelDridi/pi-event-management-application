package tn.esprit.adminmicroservice.ServiceImp;

import com.thoughtworks.xstream.mapper.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.mail.SimpleMailMessage;
import tn.esprit.adminmicroservice.Dto.ConfUserDto;
import tn.esprit.adminmicroservice.Ennum.Role;
import tn.esprit.adminmicroservice.Repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;
import tn.esprit.adminmicroservice.Service.ServiceUser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpMethod;
@Service
public class ServiceUserImpl implements ServiceUser {


    @Autowired
    RepositoryUser repositoryUser;


    @Autowired
    private JavaMailSender emailSender;

    //ajouter l'utilisateur dans la base de donnee

    @Override
    public StatusUser AcceptUserCnx(Long id) {
        List<UserDto> users = getALLUser();

        for (UserDto user : users) {
            StatusUser userSaved = null;
            if (user.getUserID().equals(id)) {
                userSaved.setFirstName(user.getFirstName());
                userSaved.setLastName(user.getLastName());
                userSaved.setEmail(user.getEmail());
                userSaved.setId(user.getUserID());
                userSaved.setRole(Role.SimpleUser);
                userSaved.setStatusUser(true);
                repositoryUser.save(userSaved);
                String emailBody = "marhbe ";
                sendEmail(user.getEmail(), "email d acceptation", emailBody);
                return userSaved;
            }

        }
        return null;
    }

//mazelet
    
    public void RefusUserCnx(Long userId){
        List<UserDto> users=getALLUser();

        // Liste des adresses e-mail bloquées
         List<String> adressesBloquees = new ArrayList<>();

        System.out.println("User Reject");
    }

    @Override
    public List<UserDto> getALLUser() {
        // Remplacer "user-service-url" par l'URL réelle du microservice utilisateur
        // Construire l'URL sans paramètres
        String userMicroserviceUrl = "http://localhost:8091/api/v1/users/all";

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

    ///post to jalel

    @Override
    public List<ConfUserDto> getAllConfirmedUsers() {

        List<StatusUser> users = repositoryUser.findAll();
        List<ConfUserDto> confUserDtos = new ArrayList<>();

        for (StatusUser user: users) {
            ConfUserDto userDto = new ConfUserDto();
            // Copy user properties to userDto
            BeanUtils.copyProperties(user, userDto);
            confUserDtos.add(userDto);
        }

        return confUserDtos;
    }

    @Override
    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("racem.messaoudi@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);
        System.out.println("Mail Sent successfully...");
    }




}
