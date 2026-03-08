package com.example.smartrestaurantreservationsystem.controllers;

import com.example.smartrestaurantreservationsystem.model.Admin;
import com.example.smartrestaurantreservationsystem.model.Restaurant;
import com.example.smartrestaurantreservationsystem.services.AdminService;
import com.example.smartrestaurantreservationsystem.services.RestaurantService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final AdminService adminService;

    public RestaurantController(RestaurantService restaurantService, AdminService adminService) {
        this.restaurantService = restaurantService;
        this.adminService = adminService;
    }

    @GetMapping
    public List<Restaurant> searchRestaurants(@RequestParam(required = false) String query) {
        if (query == null || query.isEmpty()) {
            return restaurantService.getAllRestaurants();
        }
        return restaurantService.searchRestaurants(query);
    }

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        System.out.println("DEBUG: Creating restaurant with name: " + restaurant.getName() + ", location: " + restaurant.getLocation());
        
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("DEBUG: Authentication object: " + auth);
            
            if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() == null) {
                System.out.println("DEBUG: No authenticated user");
                throw new RuntimeException("User not authenticated");
            }
            
            String email = ((User) auth.getPrincipal()).getUsername();
            System.out.println("DEBUG: Authenticated user email: " + email);
            
            Admin admin = adminService.findByEmail(email);
            System.out.println("DEBUG: Found admin: " + admin);
            
            if (admin == null) {
                System.out.println("DEBUG: Admin not found for email: " + email);
                throw new RuntimeException("Admin not found");
            }
            
            Restaurant createdRestaurant = restaurantService.createRestaurantWithAdmin(restaurant.getName(), restaurant.getLocation(), admin);
            System.out.println("DEBUG: Restaurant created successfully: " + createdRestaurant);
            
            return createdRestaurant;
        } catch (Exception e) {
            System.out.println("DEBUG: Error creating restaurant: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }


}
