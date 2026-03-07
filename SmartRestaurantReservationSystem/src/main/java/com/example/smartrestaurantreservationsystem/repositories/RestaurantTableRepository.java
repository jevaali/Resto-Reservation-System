package com.example.smartrestaurantreservationsystem.repositories;

import com.example.smartrestaurantreservationsystem.model.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    List<RestaurantTable> findByRestaurantId(Long restaurantId);

    List<RestaurantTable> findByRestaurantIdAndCapacityGreaterThanEqual(Long restaurantId, int capacity);
}
