package com.esprit.pidev.resourcemodule.controllers;

import com.esprit.pidev.resourcemodule.entities.Reservation;
import com.esprit.pidev.resourcemodule.entities.Resource;
import com.esprit.pidev.resourcemodule.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("")
    public List<Reservation> getAllReservations(){
        return  this.reservationService.getAllReservations();
    }

    @PostMapping("/addReservation/{resourceID}")
    public Reservation  addReservation(@RequestBody Reservation reservation,@PathVariable Long resourceID){
        return this.reservationService.addReservation(reservation,resourceID);
    }

    @PostMapping("/updateReservation")
    public Reservation updateReservation(@RequestBody Reservation reservation){
        return this.reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/deleteReservation/{reservationID}")
    public void deleteReservation(@PathVariable Long reservationID){

        reservationService.deleteById(reservationID);
    }

@PostMapping("/createReservation/{resourceID}")
public Reservation createReservation(@RequestBody Reservation reservation, @PathVariable Long resourceID) {
    return reservationService.createReservation(reservation, resourceID);
}




}
