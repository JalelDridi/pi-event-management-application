package com.esprit.pidev.resourcemodule.services;

import com.esprit.pidev.resourcemodule.entities.Reservation;
import com.esprit.pidev.resourcemodule.entities.Resource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations();


    Reservation addReservation();
//
//    Resource findById(Long resourceID);
//
//    Reservation createReservation(Resource resource);


    Reservation createReservation(Resource resource, Date startDate, Date endDate);

    boolean checkResourceAvailability(Resource resource, Date startDate, Date endDate);

    Reservation updateReservation(Reservation reservation);

    void deleteById(Long reservationID);


}
