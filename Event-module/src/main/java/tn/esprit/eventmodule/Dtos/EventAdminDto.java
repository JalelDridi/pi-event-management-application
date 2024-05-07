package tn.esprit.eventmodule.Dtos;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
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
    private String type;
    private String club ;
    private String status;
    @Column(name = "image", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] image;
    @Column(name = "image1", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] image1;

}


