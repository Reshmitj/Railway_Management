package com.railway.controller;

import com.railway.model.Passenger;
import com.railway.model.Reservation;
import com.railway.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/book")
    public ResponseEntity<String> bookReservation(@RequestBody Reservation reservation) {
        if (reservation.getPassenger() == null && reservation.getPassengerId() != null) {
            Passenger passenger = new Passenger();
            passenger.setId(reservation.getPassengerId());
            reservation.setPassenger(passenger);
        }

        if (reservation.getTrain() == null || reservation.getTrain().getId() == null) {
            return ResponseEntity.badRequest().body("Train ID is missing.");
        }

        String message = reservationService.bookSeat(reservation);
        return ResponseEntity.ok(message);
    }

    // ✅ New: View All Reservations for Logged-In Passenger
    @GetMapping("/list/{passengerId}")
    public ResponseEntity<List<Reservation>> getReservationsForPassenger(@PathVariable Long passengerId) {
        List<Reservation> reservations = reservationService.getReservationsByPassengerId(passengerId);
        return ResponseEntity.ok(reservations);
    }

}
