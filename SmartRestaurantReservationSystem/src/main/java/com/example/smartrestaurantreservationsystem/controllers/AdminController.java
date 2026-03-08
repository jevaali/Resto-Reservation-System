package com.example.smartrestaurantreservationsystem.controllers;

import com.example.smartrestaurantreservationsystem.DTO.AdminDTO;
import com.example.smartrestaurantreservationsystem.DTO.LoginRequest;
import com.example.smartrestaurantreservationsystem.model.Admin;
import com.example.smartrestaurantreservationsystem.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin) {
        try {
            AdminDTO savedAdmin = adminService.register(admin);
            if (savedAdmin == null) {
                return ResponseEntity.status(400).body(Map.of("message", "Email already in use"));
            }
            return ResponseEntity.ok(savedAdmin);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Server error. Please try again later."));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            AdminDTO admin = adminService.login(request.getEmail(), request.getPassword());
            if (admin == null) {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid email or password"));
            }
            return ResponseEntity.ok(admin);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Server error. Please try again later."));
        }
    }
}