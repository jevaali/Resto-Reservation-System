package com.example.smartrestaurantreservationsystem.model;


import com.example.smartrestaurantreservationsystem.model.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hall extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GridCell> gridCells;

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    public List<GridCell> getGridCells() { return gridCells; }
    public void setGridCells(List<GridCell> gridCells) { this.gridCells = gridCells; }
}

