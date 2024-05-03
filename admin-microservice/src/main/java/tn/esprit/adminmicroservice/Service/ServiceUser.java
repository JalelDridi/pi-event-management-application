package tn.esprit.adminmicroservice.Service;

import tn.esprit.adminmicroservice.Dto.ConfUserDto;
import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;

import java.io.IOException;
import java.util.List;

public interface ServiceUser  {

   List<UserDto> getALLUser();
   List<ConfUserDto> getAllConfirmedUsers();
   StatusUser AcceptUserCnx(String id);
   void sendEmail(String toEmail, String subject, String body);

   StatusUser RefusUserCnx(String userId);
   List<UserDto> getUsers();
   List<StatusUser> findAllconfUsers();

   void deletUser(String id );

   double pourcentageUsersAuth();
   String getDirection(String Destination)throws IOException, InterruptedException;

   List<StatusUser> rechercheByfonction(String fonction);

   void sendUpdateEmail(String recipientEmail,String text);
}
