package tn.esprit.adminmicroservice.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

        private Long eventId ;
        private String  Name ;
        private Date startDate ;
        private Date endDate ;
        private String type;
        private String club ;
        private String status;
        private String Image;

}
