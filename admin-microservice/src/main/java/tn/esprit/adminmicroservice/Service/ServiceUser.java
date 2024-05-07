package tn.esprit.adminmicroservice.Service;

import org.springframework.http.HttpHeaders;
import tn.esprit.adminmicroservice.Dto.ConfUserDto;
import tn.esprit.adminmicroservice.Dto.UserDto;
import tn.esprit.adminmicroservice.Entities.StatusUser;

import java.io.IOException;
import java.util.List;

public interface ServiceUser  {
   StatusUser addUser(StatusUser user);

   List<UserDto> getALLUser(HttpHeaders headers);
   List<ConfUserDto> getAllConfirmedUsers();
   StatusUser AcceptUserCnx(String id, HttpHeaders headers);
   void sendEmail(String toEmail, String subject, String body);

   StatusUser RefusUserCnx(String userId, HttpHeaders headers);
   List<UserDto> getUsers(HttpHeaders headers);
   List<StatusUser> findAllconfUsers();

   void deletUser(String id );

   double pourcentageUsersAuth(HttpHeaders headers);
   String getDirection(String Destination)throws IOException, InterruptedException;

   List<StatusUser> rechercheByfonction(String fonction);

   void sendUpdateEmail(String recipientEmail,String text);
}
