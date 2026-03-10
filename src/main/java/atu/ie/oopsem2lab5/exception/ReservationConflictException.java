package atu.ie.oopsem2lab5.exception;

public class ReservationConflictException extends RuntimeException {
    public ReservationConflictException(String timeSlotAlreadyBooked) {
        super(timeSlotAlreadyBooked);
    }
}
