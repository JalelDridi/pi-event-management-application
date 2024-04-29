package tn.esprit.eventmodule.Dtos;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import tn.esprit.eventmodule.Entities.EventType;

import java.util.Date;

@Data

public class EventNotificationDto {
    private Long eventId ;
    private String  Name ;
    private Date startDate ;
    private Date endDate ;
    private String description;

}
