package com.esprit.usermicroservice.dtos;

import lombok.Data;

@Data
public class EventUserDto {

    private String userID;
    private String firstName;
    private String lastName;
    private String email;
}
