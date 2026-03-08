package com.example.smartrestaurantreservationsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("pageTitle", "Admin Login");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("pageTitle", "Register Restaurant");
        return "register";
    }

    @GetMapping("/privacy")
    public String privacy(Model model){
        model.addAttribute("pageTitle", "Privacy Policy");
        return "privacy";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model){
        model.addAttribute("pageTitle", "Admin Dashboard");
        return "admin-dashboard";
    }
}