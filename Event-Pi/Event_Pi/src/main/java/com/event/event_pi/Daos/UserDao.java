package com.event.event_pi.Daos;

import com.event.event_pi.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
}
