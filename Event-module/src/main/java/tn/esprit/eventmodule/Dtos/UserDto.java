package tn.esprit.eventmodule.Dtos;

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

    private String userID;
    private String firstName;
    private String lastName;
    private String email;

}
