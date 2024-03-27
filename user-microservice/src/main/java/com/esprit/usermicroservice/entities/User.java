package com.esprit.usermicroservice.entities;

import com.esprit.usermicroservice.enums.Role;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userID;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private LocalDate dateOfBirth;

    private Role role;

}