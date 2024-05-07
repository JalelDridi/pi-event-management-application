package tn.esprit.notificationmodule.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationUserDto implements Serializable {

    private String fullName;
    private String email;
    private String userID;
    private String firstName;
    private String lastName;
}
