package com.example.smartrestaurantreservationsystem.DTO;

public class AdminDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;

    public void setId(Long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setEmail(String email) {this.email = email;}

    public void setPhone(String phone) {this.phone = phone;}

    public Long getId() {return id;}

    public String getName() {return name;}

    public String getEmail() {return email;}

    public String getPhone() {return phone;}
    public String getRole() {return role;}
    public void setRole(String role) {this.role = role;}
}