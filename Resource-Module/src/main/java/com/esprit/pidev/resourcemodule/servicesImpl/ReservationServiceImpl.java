package com.esprit.pidev.resourcemodule.servicesImpl;

import com.esprit.pidev.resourcemodule.daos.ReservationDao;
import com.esprit.pidev.resourcemodule.daos.ResourceDao;
import com.esprit.pidev.resourcemodule.entities.Reservation;
import com.esprit.pidev.resourcemodule.entities.Resource;
import com.esprit.pidev.resourcemodule.services.ReservationService;
import com.esprit.pidev.resourcemodule.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @jakarta.annotation.Resource
    private ResourceDao resourceDao;

    @Autowired
    private ResourceService resourceService;


    @Override
    public List<Reservation> getAllReservations() {
        return this.reservationDao.findAll();
    }


    @Override
    public Reservation addReservation(Reservation reservation,Long resourceID) {

        Resource resource= resourceDao.findById(resourceID).get();
        reservation.setResource(resource);
        return this.reservationDao.save(reservation);
    }


@Override
public Reservation updateReservation(Reservation updatedReservation, Long resourceID) {

    Reservation existingReservation= reservationDao.findById(updatedReservation.getReservationID()).orElse(null);
    if (existingReservation != null) {
        existingReservation.setStartDate(updatedReservation.getStartDate());
        existingReservation.setEndDate(updatedReservation.getEndDate());
        existingReservation.setIsValid(updatedReservation.getIsValid());
        Resource resource = resourceDao.findById(resourceID).orElse(null);
        if (resource!= null) {
            existingReservation.setResource(resource);
        }
    }
    return reservationDao.save(existingReservation);
}

    @Override
    public void deleteById(Long reservationID) {
        this.reservationDao.deleteById(reservationID);
    }




    public Reservation createReservation(Reservation reservation, Long resourceID) {
        if (resourceService.isResourceAvailableForReservation(resourceID, reservation.getStartDate()) ) {
            Resource resource = resourceService.findResourceById(resourceID);
            resource.setIsAvailable(false);
            resource.setDate(reservation.getStartDate());
            reservation.setResource(resource);
            return reservationDao.save(reservation);
        } else {
//            throw new ResourceNotAvailableException("Resource is not available for the specified date range.");
            return null;

        }
    }
//    @Override
//    public boolean checkResourceAvailability(Resource resource, Date startDate, Date endDate) {
//        // Retrieve list of reservations of a resource
//        List<Reservation> reservations = resource.getReservations();
//        if (reservations != null) {
//            // Verify if the resource is available for the specified dates
//            for (Reservation reservation : reservations) {
//                Date resStartDate = reservation.getStartDate();
//                Date resEndDate = reservation.getEndDate();
//                if (resStartDate != null && resEndDate != null) {
//                    if (resStartDate.before(endDate) && resEndDate.after(startDate)) {
//                        // If there is a conflict with an existing reservation
//                        return false;
//                    }
//                }
//            }
//        }
//        return true; // The resource is available for the specified dates
//    }






}
