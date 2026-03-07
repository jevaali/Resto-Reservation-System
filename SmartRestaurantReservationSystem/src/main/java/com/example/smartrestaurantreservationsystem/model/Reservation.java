package com.example.smartrestaurantreservationsystem.model;


import com.example.smartrestaurantreservationsystem.model.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Reservation extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "table_id")
    private RestaurantTable table; // the table being reserved

    private LocalDate date; // reservation date
    private LocalTime time; // reservation time

    private Integer partySize; // number of people

    private String customerName; // optional: name of customer

    private String customerPhone; // optional: contact info

    public RestaurantTable getTable() { return table; }
    public void setTable(RestaurantTable table) { this.table = table; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public Integer getPartySize() { return partySize; }
    public void setPartySize(Integer partySize) { this.partySize = partySize; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public Boolean getNearWindow() { return nearWindow; }
    public void setNearWindow(Boolean nearWindow) { this.nearWindow = nearWindow; }

    public Boolean getQuietCorner() { return quietCorner; }
    public void setQuietCorner(Boolean quietCorner) { this.quietCorner = quietCorner; }

    public Boolean getNearChildren() { return nearChildren; }
    public void setNearChildren(Boolean nearChildren) { this.nearChildren = nearChildren; }
}