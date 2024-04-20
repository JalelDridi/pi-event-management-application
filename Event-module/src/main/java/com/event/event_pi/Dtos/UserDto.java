package com.event.event_pi.Dtos;

import jakarta.persistence.Id;
import lombok.*;

import javax.management.relation.Role;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    @Id
    private String userID;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private LocalDate dateOfBirth;

    private Role role;
}
