package tn.esprit.adminmicroservice.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private Boolean statusUser;
}
