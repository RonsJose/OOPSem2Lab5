package atu.ie.oopsem2lab5.service;

import atu.ie.oopsem2lab5.exception.ReservationConflictException;
import atu.ie.oopsem2lab5.exception.ReservationNotFoundException;
import atu.ie.oopsem2lab5.model.Reservation;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReservartionService {
    private List<Reservation> reservations = new ArrayList<>();
    private long nextId = 1;

    public Reservation addReservation(Reservation reservation) {
        reservation.setReservationID(nextId++);

        for (Reservation existingReservation : reservations) {
            if (Objects.equals(existingReservation.getEquipmentTag(), reservation.getEquipmentTag()) && Objects.equals(existingReservation.getReservationDate(), reservation.getReservationDate())){

                int existingStart = existingReservation.getStartHour();
                int existingEnd = existingStart + existingReservation.getDurationHours();

                int newStart = reservation.getStartHour();
                int newEnd = newStart + reservation.getDurationHours();

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


    public Reservation getReservationsById(Long id) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationID() == id){
                return reservation;
            }
        }

        throw new ReservationNotFoundException("Reservation not found");
    }
}
