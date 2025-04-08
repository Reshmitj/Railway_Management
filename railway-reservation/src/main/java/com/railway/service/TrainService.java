package com.railway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railway.model.Train;
import com.railway.repository.TrainRepository;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public List<Train> searchTrains(String source, String destination) {
        return trainRepository.findBySourceAndDestination(source, destination);
    }

    public String addOrUpdateTrain(Train train) {
        Optional<Train> existingTrain = trainRepository.findByTrainNumber(train.getTrainNumber());

        // Update flow
        if (train.getId() != null) {
            if (existingTrain.isPresent() && !existingTrain.get().getId().equals(train.getId())) {
                return "Error: Train number already exists.";
            }
            trainRepository.save(train);
            return "Train updated successfully.";
        }

        // Add flow
        if (existingTrain.isPresent()) {
            return "Error: Train number already exists.";
        }

        trainRepository.save(train);
        return "Train added successfully.";
    }
    public Optional<Train> getTrainById(Long id) {
        return trainRepository.findById(id);
    }
    
    public void updateTrain(Train updatedTrain) {
        Optional<Train> existingOpt = trainRepository.findById(updatedTrain.getId());

        if (existingOpt.isPresent()) {
            Train existing = existingOpt.get();
            existing.setTrainName(updatedTrain.getTrainName());
            existing.setSource(updatedTrain.getSource());
            existing.setDestination(updatedTrain.getDestination());
            existing.setSeatsAvailable(updatedTrain.getSeatsAvailable());

            trainRepository.save(existing);
        }
    }
    
    public void deleteTrainById(Long id) {
    	trainRepository.deleteById(id);
    }



    public List<Train> getAllTrains() {
    	System.out.println("Fetched Trains: " + trainRepository.findAll());
        return trainRepository.findAll();
    }
    
    public Optional<Train> getTrainByNumber(int trainNumber) {
        return trainRepository.findByTrainNumber(trainNumber);
    }


}
