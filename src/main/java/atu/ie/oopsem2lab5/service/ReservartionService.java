package atu.ie.oopsem2lab5.service;

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
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }


}
