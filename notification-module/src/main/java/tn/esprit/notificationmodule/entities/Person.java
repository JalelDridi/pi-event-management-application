package tn.esprit.notificationmodule.entities;




import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {


    private Long cin;

    private String emailAddress;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String nationality;
}
