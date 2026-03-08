package com.example.smartrestaurantreservationsystem.model;

import com.example.smartrestaurantreservationsystem.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Restaurant extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantTable> tables;

    @ManyToMany(mappedBy = "restaurants")
    private List<Admin> admins;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<RestaurantTable> getTables() { return tables; }
    public void setTables(List<RestaurantTable> tables) { this.tables = tables; }
}
