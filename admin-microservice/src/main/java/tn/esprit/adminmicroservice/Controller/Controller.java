package tn.esprit.adminmicroservice.Controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.adminmicroservice.Dto.ConfUserDto;
import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;
import tn.esprit.adminmicroservice.Service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class Controller {

    @Autowired
    ServiceUser serviceUser;


    //constructeur pour cette classe
    public Controller(ServiceUser userService) {
        this.serviceUser = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = serviceUser.getALLUser();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/send-database")
    public List<ConfUserDto> getAllConfirmedUsers() {
        return serviceUser.getAllConfirmedUsers();
    }


    @PostMapping ("/DeleteUser/{id}")
    public  StatusUser RefusUserCnx(@PathVariable String id){

           return serviceUser.RefusUserCnx(id);
    }

    @GetMapping ("/ListFinal")
    public  List<UserDto> getUsers(){
        return serviceUser.getUsers();
    }

    @PostMapping("/accept/{id}")
    public StatusUser AcceptUserCnx(@PathVariable String id) {
            return serviceUser.AcceptUserCnx(id);
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
    public double pourcentageUsersAuth(){
        return serviceUser.pourcentageUsersAuth();
        }


    }
