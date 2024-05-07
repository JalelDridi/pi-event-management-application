package tn.esprit.notificationmodule.dtos;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NotificationEventDto implements Serializable {

    private Long eventId ;
    private String  Name ;
    private String description;
    private String Image;
    private Date startDate ;
    private Date endDate ;
    private String type;
    private String Club ;
    private String status;
    private byte[] image1;
    private String base64;

}
