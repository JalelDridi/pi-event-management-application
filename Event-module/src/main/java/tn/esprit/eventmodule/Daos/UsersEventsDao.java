package tn.esprit.eventmodule.Daos;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.eventmodule.Entities.Event;
import tn.esprit.eventmodule.Entities.Participation;
import tn.esprit.eventmodule.Entities.UserEvents;

import java.util.List;

public interface UsersEventsDao extends JpaRepository<UserEvents,Long> {
    UserEvents findByUserID(String userID);
    @Query("select ue.events from UserEvents ue where ue.userID = :userId")
    List<Event> findEventsByUserId(String userId);
}
