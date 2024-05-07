package tn.esprit.adminmicroservice.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Entity
@Data
@Getter
public class StatusEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long eventId ;
    private String  Name ;
    private Date startDate ;
    private Date endDate ;
    private String type;
    private String club ;
    private boolean status;
    private String Image;


}
