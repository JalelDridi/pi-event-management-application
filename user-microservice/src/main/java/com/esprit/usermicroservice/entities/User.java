package com.esprit.usermicroservice.entities;

import com.esprit.usermicroservice.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userID;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Temporal(TemporalType.DATE)
    private LocalDate dateOfBirth;

    private Role role;







}
