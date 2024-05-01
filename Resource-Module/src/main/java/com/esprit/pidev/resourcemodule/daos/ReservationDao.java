package com.esprit.pidev.resourcemodule.daos;

import com.esprit.pidev.resourcemodule.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ReservationDao extends JpaRepository<Reservation , Long> {
}
