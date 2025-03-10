package com.railway.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railway.model.Passenger;
import com.railway.repository.PassengerRepository;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger registerPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Optional<Passenger> login(String email, String password) {
        Optional<Passenger> passenger = passengerRepository.findByEmail(email);
        return passenger.filter(p -> p.getPassword().equals(password));
    }
}
