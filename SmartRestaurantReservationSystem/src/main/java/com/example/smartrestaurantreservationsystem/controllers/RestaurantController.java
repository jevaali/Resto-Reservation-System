package com.example.smartrestaurantreservationsystem.controllers;

import com.example.smartrestaurantreservationsystem.model.Restaurant;
import com.example.smartrestaurantreservationsystem.services.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // GET /api/restaurants?query=sushi
    @GetMapping
    public List<Restaurant> searchRestaurants(@RequestParam(required = false) String query) {
        if (query == null || query.isEmpty()) {
            return restaurantService.searchRestaurants(""); // return all
        }
        return restaurantService.searchRestaurants(query);
    }

    // POST /api/restaurants
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant.getName());
    }
}
