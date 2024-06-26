package tn.esprit.adminmicroservice.Entities;
import jakarta.persistence.*;
import lombok.*;
import tn.esprit.adminmicroservice.Ennum.Role;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StatusUser {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean statusUser;
    @Enumerated(EnumType.STRING)
    private Role role ;
    private String fonction;
    private String password;
}
