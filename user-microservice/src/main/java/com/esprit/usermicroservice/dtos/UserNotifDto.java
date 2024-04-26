package com.esprit.usermicroservice.dtos;

import lombok.Data;

@Data
public class UserNotifDto {
    private String email;
    private String subject;
    private String content;
    private String fullName;
}
