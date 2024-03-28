package com.esprit.pidev.resourcemodule.daos;

import com.esprit.pidev.resourcemodule.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDao extends JpaRepository<Reservation , Long> {
}
