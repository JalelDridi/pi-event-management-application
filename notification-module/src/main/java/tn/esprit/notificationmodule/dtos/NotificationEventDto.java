package tn.esprit.notificationmodule.dtos;


import lombok.Data;

import java.util.Date;

@Data
public class NotificationEventDto {

    private Long eventId ;
    private String  Name ;
    private String description;
    private String Image;
    private Date startDate ;
    private Date endDate ;
    private String type;
    private String Club ;
    private String status;
}
