package com.railway.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.railway.model.Passenger;
import com.railway.service.PassengerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/login")
    public String showPassengerLoginPage() {
        return "redirect:/passenger/login.html"; 
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Optional<Passenger> passenger = passengerService.login(email, password);

        if (passenger.isPresent()) {
            return "redirect:/passenger/dashboard.html";
        } else {
            return "redirect:/passenger/login.html?error=true"; 
        }
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "redirect:/passenger/register.html"; 
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Passenger passenger) {
        passengerService.registerPassenger(passenger);
        return "redirect:/passenger/login.html"; 
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/static/index.html"; 
    }
}
