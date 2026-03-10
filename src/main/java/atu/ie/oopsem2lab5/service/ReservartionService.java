package atu.ie.oopsem2lab5.service;

import atu.ie.oopsem2lab5.exception.ReservationConflictException;
import atu.ie.oopsem2lab5.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservartionService {
    private List<Reservation> reservations = new ArrayList<>();
    private long nextId = 1;

    public Reservation addReservation(Reservation reservation) {
        reservation.setReservationID(nextId++);
        reservations.add(reservation);

        for (Reservation existingReservation : reservations) {
            if (existingReservation.getEquipmentTag() == reservation.getEquipmentTag() && existingReservation.getReservationDate() == reservation.getReservationDate()) {

                int existingStart = existingReservation.getStartHour();
                int existingEnd = existingReservation.getDurationHours();

                int newStart = reservation.getStartHour();
                int newEnd = reservation.getDurationHours();

                if (existingStart < newEnd && newStart < existingEnd) {
                    reservation.setReservationID(nextId--);
                    throw new ReservationConflictException("Time slot already booked");
                }

            }
        }

        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }


}
