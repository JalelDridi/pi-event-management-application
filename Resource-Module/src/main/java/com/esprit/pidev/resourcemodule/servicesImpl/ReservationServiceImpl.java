package com.esprit.pidev.resourcemodule.servicesImpl;

import com.esprit.pidev.resourcemodule.daos.ReservationDao;
import com.esprit.pidev.resourcemodule.daos.ResourceDao;
import com.esprit.pidev.resourcemodule.entities.Reservation;
import com.esprit.pidev.resourcemodule.entities.Resource;
import com.esprit.pidev.resourcemodule.services.ReservationService;
import com.esprit.pidev.resourcemodule.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @jakarta.annotation.Resource
    private ResourceDao resourceDao;

//    @Autowired
//    ReservationService reservationService;

    @Autowired
    ResourceService resourceService;

    @Override
    public List<Reservation> getAllReservations() {
        return this.reservationDao.findAll();
    }


    @Override
    public Reservation addReservation() {
        final Reservation reservation = new Reservation();
        reservation.setStartDate(new Date());
        reservation.setEndDate(new Date());
        reservation.setIsValid(Boolean.TRUE);
        return this.reservationDao.save(reservation);
    }



    @Override
    public Reservation updateReservation(Reservation reservation) {
        return this.reservationDao.save(reservation);
    }

    @Override
    public void deleteById(Long reservationID) {
        this.reservationDao.deleteById(reservationID);
    }


    @Override
    public Reservation createReservation(Resource resource, Date startDate, Date endDate) {
        Reservation reservation = null;
        if (resource != null && startDate != null && endDate != null) {
            //verify if the resource is available of specified dates
            boolean isAvailable = checkResourceAvailability(resource, startDate, endDate);
            if (isAvailable) {
                //create a new reservation
                reservation = this.addReservation();
                reservation.setResource(resource);

                //add reservation to the list of reservations of a resource
                List<Reservation> reservations = resource.getReservations();
                if (reservations == null) {
                    reservations = new ArrayList<>();
                }
                reservations.add(reservation);
                resource.setReservations(reservations);

                //update informations of the resource
                resource.setIsAvailable(false);//mark resource not available in this period of reservation
                resource.setDate(new Date());//update the date of last reservation
                this.resourceService.addResource(resource);
            }
        }
        return reservation;
    }
    @Override
    public boolean checkResourceAvailability(Resource resource, Date startDate, Date endDate) {
        // Retrieve list of reservations of a resource
        List<Reservation> reservations = resource.getReservations();
        if (reservations != null) {
            // Verify if the resource is available for the specified dates
            for (Reservation reservation : reservations) {
                Date resStartDate = reservation.getStartDate();
                Date resEndDate = reservation.getEndDate();
                if (resStartDate != null && resEndDate != null) {
                    if (resStartDate.before(endDate) && resEndDate.after(startDate)) {
                        // If there is a conflict with an existing reservation
                        return false;
                    }
                }
            }
        }
        return true; // The resource is available for the specified dates
    }


//    @Override
//    public boolean checkResourceAvailability(Resource resource, Date startDate, Date endDate) {
//        //retrieve list of reservations of a resource
//        List<Reservation> reservations = resource.getReservations();
//        if (reservations != null) {
//            //verify if the resource is available of specified dates
//            for (Reservation reservation : reservations) {
//                if (reservation.getStartDate().before(endDate) && reservation.getEndDate().after(startDate)) {
//                    //if there is a confusion with an existing reservation
//                    return false;
//                }
//            }
//        }
//        return true;//the resource is now available on specified dates
//    }



}
