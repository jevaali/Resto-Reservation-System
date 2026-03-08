package com.example.smartrestaurantreservationsystem.services;

import com.example.smartrestaurantreservationsystem.model.Admin;
import com.example.smartrestaurantreservationsystem.model.Restaurant;
import com.example.smartrestaurantreservationsystem.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AdminService adminService;

    public RestaurantService(RestaurantRepository restaurantRepository, AdminService adminService) {
        this.restaurantRepository = restaurantRepository;
        this.adminService = adminService;
    }

    public List<Restaurant> searchRestaurants(String query) {
        return restaurantRepository.findByNameContainingIgnoreCase(query);
    }

    public Restaurant createRestaurant(String name, String location) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setLocation(location);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant createRestaurantWithAdmin(String name, String location, Admin admin) {
        System.out.println("DEBUG: RestaurantService.createRestaurantWithAdmin called with name: " + name + ", location: " + location + ", admin: " + admin);
        
        try {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(name);
            restaurant.setLocation(location);
            restaurant = restaurantRepository.save(restaurant);
            System.out.println("DEBUG: Restaurant saved: " + restaurant);
            
            // Associate restaurant with admin
            if (admin.getRestaurants() == null) {
                System.out.println("DEBUG: Admin restaurants list is null, initializing");
                admin.setRestaurants(new ArrayList<>());
            }
            
            admin.getRestaurants().add(restaurant);
            System.out.println("DEBUG: Restaurant added to admin's restaurants list");
            
            adminService.save(admin); // Save the admin entity to persist the association
            System.out.println("DEBUG: Admin saved with restaurant association");
            
            return restaurant;
        } catch (Exception e) {
            System.out.println("DEBUG: Error in RestaurantService.createRestaurantWithAdmin: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }
}