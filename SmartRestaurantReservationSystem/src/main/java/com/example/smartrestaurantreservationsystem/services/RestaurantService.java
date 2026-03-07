package com.example.smartrestaurantreservationsystem.services;

import com.example.smartrestaurantreservationsystem.model.Restaurant;
import com.example.smartrestaurantreservationsystem.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> searchRestaurants(String query) {
        return restaurantRepository.findByNameContainingIgnoreCase(query);
    }

    public Restaurant createRestaurant(String name) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        return restaurantRepository.save(restaurant);
    }
}