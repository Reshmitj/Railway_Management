package com.railway.service;

import com.railway.model.Route;
import com.railway.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public void saveRoute(Route route) {
        routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public boolean isDuplicateForTrain(Long trainId) {
        return routeRepository.existsByTrainId(trainId);
    }
}
