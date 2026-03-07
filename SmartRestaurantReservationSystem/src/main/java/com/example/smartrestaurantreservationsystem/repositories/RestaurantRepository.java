package com.example.smartrestaurantreservationsystem.repositories;

import com.example.smartrestaurantreservationsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByName(String name);

    List<Restaurant> findByNameContainingIgnoreCase(String partialName);
}