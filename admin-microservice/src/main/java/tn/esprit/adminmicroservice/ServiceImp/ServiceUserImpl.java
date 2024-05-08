package tn.esprit.adminmicroservice.ServiceImp;

import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
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

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;
@Service
public class ServiceUserImpl implements ServiceUser {


    @Autowired
    RepositoryUser repositoryUser;




    @Autowired
    private JavaMailSender emailSender;

    //ajouter l'utilisateur dans la base de donnee

    @Override
    public StatusUser addUser(StatusUser user){
        user.setRole(Role.Admin);
        return repositoryUser.save(user);
    }

    @Override
    public StatusUser AcceptUserCnx(String id, HttpHeaders headers) {
        List<UserDto> users = getALLUser(headers);

        for (UserDto user : users) {
            StatusUser userSaved=new StatusUser();

            if (user.getUserID().equals(id)) {
                userSaved.setFirstName(user.getFirstName());
                userSaved.setLastName(user.getLastName());
                userSaved.setEmail(user.getEmail());
                userSaved.setId(user.getUserID());
                userSaved.setRole(Role.SimpleUser);
                userSaved.setStatusUser(true);
                repositoryUser.save(userSaved);
                String emailBody = "bienvenu dans notre application ";
                sendEmail(user.getEmail(), "email d acceptation", emailBody);
                return userSaved;
            }

        }
        return null;
    }

//mazelet
@Override
public StatusUser RefusUserCnx(String id, HttpHeaders headers) {
    List<UserDto> users = getALLUser(headers);

    for (UserDto user : users) {
        StatusUser userSaved=new StatusUser();

        if (user.getUserID().equals(id)) {
            userSaved.setFirstName(user.getFirstName());
            userSaved.setLastName(user.getLastName());
            userSaved.setEmail(user.getEmail());
            userSaved.setId(user.getUserID());
            userSaved.setRole(Role.SimpleUser);
            userSaved.setStatusUser(false);
            repositoryUser.save(userSaved);
            return userSaved;
        }

    }
    return null;
}
    @Override
    public List<UserDto> getUsers(HttpHeaders headers) {
        // Retrieve all users and statuses
        List<UserDto> users = getALLUser(headers);
        List<UserDto> test = new ArrayList<>();

        // Retrieve all statuses
        List<StatusUser> statuses = repositoryUser.findAll();

        // Filter out users whose ID exists in the statuses list
        test = users.stream()
                .filter(user -> statuses.stream().noneMatch(status -> status.getId().equals(user.getUserID())))
                .collect(Collectors.toList());

        return test;
    }




    @Override
    public List<UserDto> getALLUser(HttpHeaders headers) {
        // Remplacer "user-service-url" par l'URL réelle du microservice utilisateur
        // Construire l'URL sans paramètres
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8060/api/v1").build();

        // Faire une requête GET au microservice utilisateur pour récupérer la liste des utilisateurs
        Mono<ResponseEntity<List<UserDto>>> responseMono = webClient.get()
                .uri("/users/all")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .toEntityList(UserDto.class);

        // Block and retrieve the response
        ResponseEntity<List<UserDto>> responseEntity = responseMono.block();

        // Extract the list of users
        assert responseEntity != null;
        List<UserDto> usersList = responseEntity.getBody();
        // Vérifier si la requête a réussi (code de statut HTTP 200)
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody(); // Retourner la liste des utilisateurs
        } else {
            // Gérer le cas où la requête n'a pas réussi
            // Vous pouvez lancer une exception ou le gérer en fonction des besoins de votre application
            throw new RuntimeException("Failed to fetch users from User Microservice. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    @Override
    public List<StatusUser> findAllconfUsers() {

        List<StatusUser> listaff = new ArrayList<>();

        // Retrieve all statuses
        List<StatusUser> users = repositoryUser.findAll();
        for (StatusUser user : users) {
            if (user.getStatusUser().equals(true)) {
                listaff.add(user);
            }

        }
        return listaff;
    }

    ///post to Jalel-UserAccepter

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

    @Override
 public void deletUser(String id ){
     List<StatusUser> users = repositoryUser.findAll();
     for (StatusUser user : users) {
         if (user.getId().equals(id)) {
            repositoryUser.delete(user);
         }

     }
 }


 /////fonction de statistique
@Override
public double pourcentageUsersAuth(HttpHeaders headers) {
    List<StatusUser> users = repositoryUser.findAll();
    List<UserDto> usersAuth = getUsers(headers);
    int totalUsers = users.size();
    int authenticatedUsers = usersAuth.size();
    if (authenticatedUsers == 0) {
        return 0.0;
    }
    double percentage = (double) authenticatedUsers / totalUsers * 100;
    return Math.round(percentage * 100.0) / 100.0;
}
/*******************google maps Api********************/


class DirectionsResponse {
    public List<Route> routes;
}

    class Route {
        public List<Leg> legs;
    }

    class Leg {
        public List<Step> steps;
    }

    class Step {
        public String htmlInstructions;
    }

private static final String API_KEY="AIzaSyDhYovKH9L-4572jB09MH00LcOWOR02FCA";

    public String getDirection(String destination) throws IOException, InterruptedException {
        String encodedDestination = URLEncoder.encode(destination, "UTF-8");

        String url = "https://maps.googleapis.com/maps/api/directions/json?origin=tunis&destination="
                + encodedDestination + "&key=" + API_KEY;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        DirectionsResponse directionsResponse = gson.fromJson(response.body(), DirectionsResponse.class);

        if (directionsResponse.routes != null && !directionsResponse.routes.isEmpty()) {
            Route route = directionsResponse.routes.get(0);
            if (route.legs != null && !route.legs.isEmpty()) {
                Leg leg = route.legs.get(0);
                if (leg.steps != null && !leg.steps.isEmpty()) {
                    String routeInstructions = leg.steps.stream()
                            .map(step -> step.htmlInstructions)
                            .collect(Collectors.joining("\n"));
                    return routeInstructions;
                } else {
                    return "Aucune étape trouvée pour cette destination.";
                }
            } else {
                return "Aucune jambe trouvée pour cette destination.";
            }
        } else {
            return "Aucune route trouvée pour cette destination.";
        }
    }



    @Override
    public List<StatusUser> rechercheByfonction(String fonction){
         return repositoryUser.findByFonction(fonction);
    }

@Override
    public void sendUpdateEmail(String recipientEmail,String text) {
        sendEmail(recipientEmail, "email for update", text);
    }




}
