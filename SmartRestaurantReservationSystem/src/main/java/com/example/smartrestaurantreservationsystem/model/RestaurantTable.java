package com.example.smartrestaurantreservationsystem.model;

import com.example.smartrestaurantreservationsystem.model.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class RestaurantTable extends BaseEntity {

    private int capacity;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<GridCell> occupiedCells;

    @OneToMany(mappedBy = "table")
    private List<Reservation> reservations;

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    public List<GridCell> getOccupiedCells() { return occupiedCells; }
    public void setOccupiedCells(List<GridCell> occupiedCells) { this.occupiedCells = occupiedCells; }

}
