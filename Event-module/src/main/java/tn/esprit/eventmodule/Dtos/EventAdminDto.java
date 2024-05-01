package tn.esprit.eventmodule.Dtos;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import tn.esprit.eventmodule.Entities.EventType;
import tn.esprit.eventmodule.Entities.StatusType;

import java.util.Date;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventAdminDto {
    private Long eventId ;
    private String  Name ;
    private Date startDate ;
    private Date endDate ;
    @Enumerated( EnumType.STRING)
    @Column
    private EventType type;
    private String club ;
    @Column
    private StatusType status;
    private String Image;

}
