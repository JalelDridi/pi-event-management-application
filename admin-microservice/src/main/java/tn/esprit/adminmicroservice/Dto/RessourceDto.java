package tn.esprit.adminmicroservice.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import tn.esprit.adminmicroservice.Entities.StatusRessources;

import java.util.Date;

public class RessourceDto {


    private Long reservationID;
    private String resourceType;
    private Date startDate;
    private Date endDate;
    private int nbreReserve;
    private int quantite;

}
