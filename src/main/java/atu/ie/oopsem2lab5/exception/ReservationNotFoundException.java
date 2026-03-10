package atu.ie.oopsem2lab5.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String reservationNotFound) {
        super(reservationNotFound);
    }
}
