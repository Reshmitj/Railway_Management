package com.railway.service;

import com.railway.model.Train;
import com.railway.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public List<Train> searchTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }

    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    public List<Train> getAllTrains() {
    	System.out.println("Fetched Trains: " + trainRepository.findAll());
        return trainRepository.findAll();
    }
}
