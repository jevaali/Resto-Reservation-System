package com.example.smartrestaurantreservationsystem.model;


import com.example.smartrestaurantreservationsystem.model.base.BaseEntity;
import com.example.smartrestaurantreservationsystem.model.enums.CellType;
import jakarta.persistence.*;

@Entity
public class GridCell extends BaseEntity {

    private int x;
    private int y;

    @Enumerated(EnumType.STRING)
    private CellType type;

    @ManyToOne
    private RestaurantTable table;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public CellType getType() { return type; }
    public void setType(CellType type) { this.type = type; }

    public RestaurantTable getTable() { return table; }
    public void setTable(RestaurantTable table) { this.table = table; }

}
