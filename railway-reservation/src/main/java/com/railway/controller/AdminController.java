package com.railway.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.railway.model.Route;
import com.railway.model.Train;
import com.railway.service.AdminService;
import com.railway.service.RouteService;
import com.railway.service.TrainService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private RouteService routeService;

    // Show Admin Login Page
    @GetMapping("/login")
    public String showAdminLoginPage() {
        return "redirect:/admin/login.html";
    }

    // Handle Admin Login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        Optional<com.railway.model.Admin> admin = adminService.login(username, password);
        return admin.isPresent()
                ? "redirect:/admin/dashboard.html"
                : "redirect:/admin/login.html?error=true";
    }

    // Dashboard Access
    @GetMapping("/dashboard")
    public String showAdminDashboard(HttpSession session) {
        session.setAttribute("trains", trainService.getAllTrains());
        return "redirect:/admin/dashboard.html";
    }

    // 🛤️ Add or Update Train
    @PostMapping("/addTrain")
    public String addOrUpdateTrain(@ModelAttribute Train train, HttpSession session) {
        String message = trainService.addOrUpdateTrain(train);
        session.setAttribute("trainMessage", message);
        return "redirect:/admin/dashboard.html";
    }

    // ✏️ Update Train (AJAX)
    @PutMapping("/updateTrain")
    @ResponseBody
    public ResponseEntity<String> updateTrain(@RequestBody Train train) {
        try {
            trainService.updateTrain(train);
            return ResponseEntity.ok("Train updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating train");
        }
    }

    // ❌ Delete Train (AJAX)
    @DeleteMapping("/deleteTrain/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteTrain(@PathVariable Long id) {
        trainService.deleteTrainById(id);
        return ResponseEntity.ok("Deleted");
    }

    @PostMapping("/addRoute")
    public String addRoute(@RequestParam int trainNumber,
                           @RequestParam String source,
                           @RequestParam String destination,
                           @RequestParam(required = false) String stops,
                           HttpSession session) {
        System.out.println("Received Add Route Request:");
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Stops Raw: " + stops);

        Optional<Train> trainOpt = trainService.getTrainByNumber(trainNumber);

        if (trainOpt.isEmpty()) {
            session.setAttribute("routeMessage", "❌ Train not found for number: " + trainNumber);
            return "redirect:/admin/dashboard.html";
        }

        Train train = trainOpt.get();

        if (routeService.isDuplicateForTrain(train.getId())) {
            session.setAttribute("routeMessage", "⚠️ Route already exists for train: " + trainNumber);
            return "redirect:/admin/dashboard.html";
        }

        Route route = new Route();
        route.setTrain(train);
        route.setSource(source);
        route.setDestination(destination);

        if (stops != null && !stops.isBlank()) {
            List<String> stopList = Arrays.stream(stops.split(","))
                                          .map(String::trim)
                                          .toList();
            route.setStops(stopList);
        }

        routeService.saveRoute(route);
        session.setAttribute("routeMessage", "✅ Route added successfully for train: " + trainNumber);
        return "redirect:/admin/dashboard.html";
    }





    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login.html";
    }
}
