package tn.esprit.adminmicroservice.Dto;

import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RessourceDto {


    private Long reservationID;
    private String resourceType;
    private Date startDate;
    private Date endDate;
    private int nbreReserve;
    private int quantite;

}
