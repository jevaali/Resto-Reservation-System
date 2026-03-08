package com.example.smartrestaurantreservationsystem.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model, HttpSession session){
        addSessionAttributesToModel(model, session);
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("pageTitle", "Admin Login");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, HttpSession session){
        addSessionAttributesToModel(model, session);
        model.addAttribute("pageTitle", "Register Restaurant");
        return "register";
    }

    @GetMapping("/privacy")
    public String privacy(Model model, HttpSession session){
        addSessionAttributesToModel(model, session);
        model.addAttribute("pageTitle", "Privacy Policy");
        return "privacy";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model, HttpSession session){
        addSessionAttributesToModel(model, session);
        model.addAttribute("pageTitle", "Admin Dashboard");
        return "admin-dashboard";
    }

    private void addSessionAttributesToModel(Model model, HttpSession session) {
        model.addAttribute("adminId", session.getAttribute("adminId"));
        model.addAttribute("adminName", session.getAttribute("adminName"));
        model.addAttribute("adminEmail", session.getAttribute("adminEmail"));
        model.addAttribute("adminRole", session.getAttribute("adminRole"));
    }
}