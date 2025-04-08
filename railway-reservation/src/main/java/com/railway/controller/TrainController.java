package com.railway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.railway.model.Train;
import com.railway.service.TrainService;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/search")
    public List<Train> searchTrains(@RequestParam String source, @RequestParam String destination) {
        return trainService.searchTrains(source, destination);
    }
    
    @GetMapping("")
    public List<Train> getAllTrains() {
        return trainService.getAllTrains();
    }
    
    @GetMapping("/{id}")
    public Train getTrain(@PathVariable Long id) {
        return trainService.getTrainById(id).orElse(null);
    }
    @PutMapping("/updateTrain")
    public ResponseEntity<String> updateTrain(@RequestBody Train train) {
        trainService.addOrUpdateTrain(train);
        return ResponseEntity.ok("Train updated successfully");
    }



}
