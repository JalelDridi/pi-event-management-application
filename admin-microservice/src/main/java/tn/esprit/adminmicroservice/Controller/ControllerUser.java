package tn.esprit.adminmicroservice.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tn.esprit.adminmicroservice.Dto.ConfUserDto;
import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;
import tn.esprit.adminmicroservice.Service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;


    //constructeur pour cette classe
    public ControllerUser(ServiceUser userService) {
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



    @PostMapping("/accept")
    public StatusUser AcceptUserCnx(@PathVariable Long id) {
            return serviceUser.AcceptUserCnx(id);
        }

    }
