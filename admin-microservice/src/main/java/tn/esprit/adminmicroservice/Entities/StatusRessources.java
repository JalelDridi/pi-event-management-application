package tn.esprit.adminmicroservice.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusRessources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Long id_Ressource;
    private  String type;
    private int quntite;
    private int nbrReservation;
}
