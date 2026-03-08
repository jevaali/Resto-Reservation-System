package com.example.smartrestaurantreservationsystem.controllers;

import com.example.smartrestaurantreservationsystem.model.Admin;
import com.example.smartrestaurantreservationsystem.services.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    private final AdminService adminService;

    public AdminPageController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return "redirect:/login";
        }

        String email = ((User) auth.getPrincipal()).getUsername();
        Admin admin = adminService.findByEmail(email);

        model.addAttribute("adminName", admin.getName());
        model.addAttribute("pageTitle", "Admin Dashboard");

        model.addAttribute("content", "admin-dashboard :: adminContent");
        model.addAttribute("restaurants", admin.getRestaurants());

        return "_layout";
    }
}