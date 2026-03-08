package com.example.smartrestaurantreservationsystem.model;

import com.example.smartrestaurantreservationsystem.model.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Admin extends BaseEntity {

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String password;

    @ManyToMany
    @JoinTable(
            name = "admin_restaurants",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> restaurants;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Restaurant> getRestaurants() { return restaurants; }
    public void setRestaurants(List<Restaurant> restaurants) { this.restaurants = restaurants; }
}
