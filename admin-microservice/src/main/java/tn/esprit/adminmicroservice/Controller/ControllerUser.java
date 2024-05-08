package tn.esprit.adminmicroservice.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import tn.esprit.adminmicroservice.Dto.ConfUserDto;
import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;
import tn.esprit.adminmicroservice.Service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;


    //constructeur pour cette classe
    public ControllerUser(ServiceUser userService) {
        this.serviceUser = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader("Authorization") String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(authorizationHeader);
        headers.set("Authorization", authorizationHeader);
        List<UserDto> users = serviceUser.getALLUser(headers);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/send-database")
    public List<ConfUserDto> getAllConfirmedUsers() {
        return serviceUser.getAllConfirmedUsers();
    }


    @PostMapping ("/DeleteUser/{id}")
    public  StatusUser RefusUserCnx(@PathVariable String id,
                                    @RequestHeader("Authorization") String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(authorizationHeader);
        headers.set("Authorization", authorizationHeader);

           return serviceUser.RefusUserCnx(id, headers);
    }

    @GetMapping ("/ListFinal")
    public  List<UserDto> getUsers(@RequestHeader("Authorization") String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(authorizationHeader);
        headers.set("Authorization", authorizationHeader);
        return serviceUser.getUsers(headers);
    }

    @PostMapping("/accept/{id}")
    public StatusUser AcceptUserCnx(@PathVariable String id,
                                    @RequestHeader("Authorization") String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(authorizationHeader);
        headers.set("Authorization", authorizationHeader);
            return serviceUser.AcceptUserCnx(id, headers);
        }

        @GetMapping("/UsersConfirmer")
    public  List<StatusUser> findAllconfUsers(){
        return serviceUser.findAllconfUsers();
        }

        @DeleteMapping("/fasakh/{id}")
    public void deletUser(@PathVariable String id ){
        serviceUser.deletUser(id);
        }


        @GetMapping("/satistique")
    public double pourcentageUsersAuth(@RequestHeader("Authorization") String authorizationHeader) {
            HttpHeaders headers = new HttpHeaders();
            System.out.println(authorizationHeader);
            headers.set("Authorization", authorizationHeader);
        return serviceUser.pourcentageUsersAuth(headers);
        }


    private static final String API_KEY = "AIzaSyDhYovKH9L-4572jB09MH00LcOWOR02FCA";

    @GetMapping("/directions")
    public String getDirection(@RequestParam("destination") String destination) {
        try {

            return serviceUser.getDirection(destination);
        } catch (IOException | InterruptedException e) {
            // Gérer les exceptions ici, par exemple, renvoyer une réponse d'erreur
            return "Erreur lors de la récupération des directions : " + e.getMessage();
        }
    }


    @GetMapping("/suivantFonction")
    public List<StatusUser> rechercheByfonction(@RequestParam String fonction){

        return serviceUser.rechercheByfonction(fonction);
    }

    @GetMapping("/mailSender")
   public void sendUpdateEmail(@RequestParam String recipientEmail, @RequestParam String mail){
        serviceUser.sendUpdateEmail(recipientEmail,mail);
    }

    @PostMapping("/addAdmin")
    public StatusUser addUser(@RequestBody StatusUser user){
        return serviceUser.addUser(user);
    }



    ///////////////ressources////////////




    }
