package tn.esprit.notificationmodule.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class NotificationEventDto {


    private Long eventId ;
    private String  Name ;
    private Date startDate ;
    private Date endDate ;
    private String description;
}
