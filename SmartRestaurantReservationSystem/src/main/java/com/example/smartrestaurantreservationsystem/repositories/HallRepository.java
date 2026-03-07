package com.example.smartrestaurantreservationsystem.repositories;

import com.example.smartrestaurantreservationsystem.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<Hall, Long> {

    List<Hall> findByRestaurantId(Long restaurantId);
}
