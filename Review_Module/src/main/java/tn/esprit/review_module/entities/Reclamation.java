package tn.esprit.review_module.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Reclamations")
public class Reclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdRec;
    private Long eventId;
    private String userId;
    private TypeReclamation TypeRec;
    private LocalDateTime DateReclamation;


}
