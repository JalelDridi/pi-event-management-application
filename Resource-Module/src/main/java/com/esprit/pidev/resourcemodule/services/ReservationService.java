package com.esprit.pidev.resourcemodule.services;

import com.esprit.pidev.resourcemodule.entities.Reservation;
import com.esprit.pidev.resourcemodule.entities.Resource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();


    Reservation addReservation(Reservation reservation,Long resourceID);

  Reservation createReservation(Reservation reservation, Long resourceId);

    Reservation updateReservation(Reservation reservation);

    void deleteById(Long reservationID);



}
