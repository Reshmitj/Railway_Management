package com.railway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.railway.model.Admin;
import com.railway.model.Train;
import com.railway.service.AdminService;
import com.railway.service.TrainService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TrainService trainService;

    // Show Admin Login Page
    @GetMapping("/login")
    public String showAdminLoginPage() {
        return "redirect:/admin/login.html";  // Redirect to the static login page
    }

    // Handle Admin Login
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        Optional<Admin> admin = adminService.login(username, password);

        if (admin.isPresent()) {
            return "redirect:/admin/dashboard.html";  // Redirect to the dashboard after successful login
        } else {
            return "redirect:/admin/login.html?error=true";  // Redirect back to login with an error
        }
    }

    // Show Admin Dashboard with Train List
    @GetMapping("/dashboard")
    public String showAdminDashboard(HttpSession session) {
        List<Train> trainList = trainService.getAllTrains();  // Fetch the train list
        session.setAttribute("trains", trainList);  // Store the train list in session
        return "redirect:/admin/dashboard.html";  // Redirect to the static dashboard page
    }

    // Add Train Functionality
    @PostMapping("/addTrain")
    public String addTrain(@ModelAttribute Train train) {
        trainService.addTrain(train);
        return "redirect:/admin/dashboard";  // Redirect to refresh the dashboard after adding the train
    }

    // Logout functionality
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the session to log out
        return "redirect:/admin/login.html";  // Redirect to the login page after logout
    }
}
