package atu.ie.oopsem2lab5.controller;

import atu.ie.oopsem2lab5.model.Reservation;
import atu.ie.oopsem2lab5.service.ReservartionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservartionService reservationService;

    public ReservationController(ReservartionService reservationService) {
        this.reservationService = reservationService;
    }

    //Create

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation) {
        Reservation reservation1 = reservationService.addReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation1);
    }

    //Retrieving Information
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getReservations());
    }
}
