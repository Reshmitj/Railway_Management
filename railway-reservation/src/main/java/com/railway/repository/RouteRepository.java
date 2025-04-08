package com.railway.repository;

import com.railway.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
    boolean existsByTrainId(Long trainId);
}
