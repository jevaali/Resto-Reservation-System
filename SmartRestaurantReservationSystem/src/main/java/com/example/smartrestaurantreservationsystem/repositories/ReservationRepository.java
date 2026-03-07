package com.example.smartrestaurantreservationsystem.repositories;

import com.example.smartrestaurantreservationsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByTableIdAndDate(Long tableId, LocalDate date);

    List<Reservation> findByTableIdAndDateAndTime(Long tableId, LocalDate date, LocalTime time);

    List<Reservation> findByTableIdInAndDate(List<Long> tableIds, LocalDate date);

}
