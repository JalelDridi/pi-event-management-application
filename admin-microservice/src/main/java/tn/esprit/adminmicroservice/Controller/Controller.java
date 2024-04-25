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



    @PostMapping("/accept")
    public StatusUser AcceptUserCnx(@RequestParam String id) {
            return serviceUser.AcceptUserCnx(id);
        }

    }
