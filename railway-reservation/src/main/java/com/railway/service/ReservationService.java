package com.railway.service;

import com.railway.model.Passenger;
import com.railway.model.Reservation;
import com.railway.model.Train;
import com.railway.repository.PassengerRepository;
import com.railway.repository.ReservationRepository;
import com.railway.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private TrainRepository trainRepo;

    @Autowired
    private PassengerRepository passengerRepo;

    public String bookSeat(Reservation reservation) {
        Long passengerId = reservation.getPassenger() != null ? reservation.getPassenger().getId() : null;
        Long trainId = reservation.getTrain() != null ? reservation.getTrain().getId() : null;

        if (passengerId == null || trainId == null) {
            return "Booking Failed: Passenger or Train ID is missing.";
        }

        Passenger passenger = passengerRepo.findById(passengerId).orElse(null);
        Train train = trainRepo.findById(trainId).orElse(null);

        if (passenger == null) {
            return "Booking Failed: Invalid Passenger ID.";
        }

        if (train == null || train.getSeatsAvailable() < reservation.getSeatsBooked()) {
            return "Booking Failed: Not enough seats available.";
        }

        reservation.setPassenger(passenger);
        reservation.setTrain(train);

        train.setSeatsAvailable(train.getSeatsAvailable() - reservation.getSeatsBooked());
        trainRepo.save(train);
        reservationRepo.save(reservation);

        return "Booking Successful!";
    }

    public List<Reservation> getReservationsByPassengerId(Long passengerId) {
        return reservationRepo.findByPassenger_Id(passengerId); 
    }

}
