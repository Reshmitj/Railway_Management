package com.railway.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.railway.model.Passenger;
import com.railway.service.PassengerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    // Show login page
    @GetMapping("/login")
    public String showPassengerLoginPage() {
        return "redirect:/passenger/login.html";
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Optional<Passenger> passenger = passengerService.login(email, password);

        if (passenger.isPresent()) {
            session.setAttribute("passengerId", passenger.get().getId());
            System.out.println("✅ Login success. Passenger ID stored in session: " + passenger.get().getId());
            return "redirect:/passenger/dashboard.html";
        } else {
            System.out.println("❌ Login failed for email: " + email);
            return "redirect:/passenger/login.html?error=true";
        }
    }

    // Show registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "redirect:/passenger/register.html";
    }

    // Handle registration
    @PostMapping("/register")
    public String register(@ModelAttribute Passenger passenger) {
        passengerService.registerPassenger(passenger);
        System.out.println("✅ Registered passenger: " + passenger.getEmail());
        return "redirect:/passenger/login.html";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println("🚪 Logging out passenger with session ID: " + session.getAttribute("passengerId"));
        session.invalidate();
        return "redirect:/static/index.html";
    }

    // Get session passenger ID (used in dashboard.js to attach ID to booking)
    @GetMapping("/session-id")
    @ResponseBody
    public ResponseEntity<Long> getLoggedInPassengerId(HttpSession session) {
        Object idObj = session.getAttribute("passengerId");
        System.out.println("📦 Session passengerId attribute: " + idObj);
        try {
            if (idObj != null) {
                Long id = Long.valueOf(idObj.toString()); // Safe cast
                return ResponseEntity.ok(id);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            System.err.println("❌ Error retrieving session ID: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
