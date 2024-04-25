package tn.esprit.adminmicroservice.Service;

import tn.esprit.adminmicroservice.Dto.ConfUserDto;
import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;

import java.util.List;

public interface ServiceUser  {

   List<UserDto> getALLUser();
   List<ConfUserDto> getAllConfirmedUsers();
   StatusUser AcceptUserCnx(Long id);
   void sendEmail(String toEmail, String subject, String body);
}
