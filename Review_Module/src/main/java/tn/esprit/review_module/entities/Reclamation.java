package tn.esprit.review_module.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


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
    private String content;
    @Enumerated(EnumType.STRING)
    private TypeReclamation TypeRec;
    private OffsetDateTime dateReclamation;



}