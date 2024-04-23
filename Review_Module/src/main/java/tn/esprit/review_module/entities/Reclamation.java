package tn.esprit.review_module.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;



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
    @Enumerated(EnumType.STRING)
    private TypeReclamation TypeRec;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate DateReclamation;



}
