package tn.esprit.eventmodule.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {
    private Long reservationID;
    private Date startDate;
    private Date endDate;
    private Boolean isValid;
    private ResourceDto resourceDto;
}
