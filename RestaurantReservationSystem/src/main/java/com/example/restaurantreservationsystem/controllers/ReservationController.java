package com.example.restaurantreservationsystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @GetMapping("/orders")
    public String orders(@RequestParam String item){

        return "Your order is: " + item;
    }
}
