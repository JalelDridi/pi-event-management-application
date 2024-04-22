package org.example.microservicenadine.Controller;

import org.example.microservicenadine.Dto.UserDto;
import org.example.microservicenadine.Service.ServiceUser;
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

}
