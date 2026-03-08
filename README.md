# Smart Restaurant Reservation System

> **Note:** This version of the Smart Restaurant Reservation System represents the initial implementation. Core functionalities such as account management, basic restaurant configuration and reservation viewing are available but not fully tested. Many features, including reservation editing, notifications, waitlist management, analytics, and full account/restaurant settings, are planned for future development. 

A modern, intelligent restaurant reservation and management system to be built with Spring Boot. This application will allow restaurant owners to manage their restaurants, halls, tables, and reservations efficiently, while providing customers with an easy way to find and book tables.

## Features

### For Customers
- **Restaurant Discovery**: Browse available restaurants with search functionality
- **Table Booking**: Reserve tables in restaurants with various table types and sizes
- **Interactive Hall Visualization**: View restaurant layouts with different cell types (tables, blocked areas, windows, terraces, etc.)

### For Restaurant Owners/Admins
- **Admin Dashboard**: Centralized management interface
- **Restaurant Management**: Add, edit, and manage multiple restaurants
- **Hall Management**: Create and configure restaurant halls with interactive grid system
- **Table Configuration**: Define various table sizes and types
- **Reservation Management**: Track and manage customer reservations
- **User Authentication**: Secure login and registration system

### Technical Features
- **Spring Boot 4.0.3**: Modern Java web framework
- **Thymeleaf**: Server-side template engine
- **Spring Security**: Authentication and authorization
- **H2 Database**: In-memory/disk-based database for development
- **Spring Data JPA**: ORM for database operations
- **Responsive Design**: Mobile-friendly user interface
- **Interactive UI**: JavaScript-driven interactive elements

## Tech Stack

### Backend
- **Java 25**
- **Spring Boot 4.0.3**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database**

### Frontend
- **Thymeleaf**
- **HTML5/CSS3**
- **JavaScript (ES6+)**
- **Bootstrap-inspired CSS**

### Build Tool
- **Maven**

## Getting Started

### Prerequisites
- Java 25 or higher
- Maven 3.8.0 or higher
- Any modern web browser

### Installation

Check QUICK-START.md

### Default H2 Database Configuration

The application uses H2 database in file mode:
- **URL**: `jdbc:h2:file:./data/reservationdb`
- **Username**: `sa`
- **Password**: (empty)

H2 Console is available at:
```
http://localhost:8080/h2-console
```

## Usage Guide

### Admin Registration and Login

1. **Register as Admin**:
   - Go to `/register`
   - Fill in name, email, and password
   - Click "Register"

2. **Login**:
   - Go to `/login`
   - Enter your email and password
   - Click "Login"

### Managing Restaurants

1. **Add New Restaurant**:
   - From admin dashboard, click "Add New Restaurant"
   - Fill in restaurant details (name, location)
   - Click "Add Restaurant"

2. **View Restaurants**:
   - All your restaurants are displayed on the admin dashboard
   - Click "Settings (manage hall)" to configure restaurant halls
   - Click "Reservations" to view and manage bookings

### Hall Management (Currently not working quite right)

1. **Add Hall**:
   - In restaurant settings, click "Add Hall"
   - Enter hall name, width, and height
   - Click "Add Hall"

2. **Configure Hall Layout**:
   - Use cell type selector to choose cell types (Empty, Blocked, Window, Terrace, Children, Private, Table)
   - Select table size (2-4 people, 4-6 people, 6-8 people)
   - Click "Add Table" to place tables
   - Click cells to change their type
   - Click "Save Changes" to persist layout

3. **Clear Hall**:
   - Click "Clear All" to reset the entire hall layout

### Reservations Management 

1. **View Reservations**:
   - From admin dashboard, click "Reservations" for a restaurant
   - See all upcoming and past reservations

2. **Manage Reservations**:
   - (Feature to be implemented: Edit, cancel, or confirm reservations)

## API Documentation

### Admin Endpoints

- **POST /api/admin/register**: Register new admin user
- **POST /api/admin/login**: Admin login (via Spring Security)

### Restaurant Endpoints

- **GET /api/restaurants**: Get all restaurants or search by query
- **POST /api/restaurants**: Create new restaurant (requires authentication)

### Page Endpoints

- **GET /**: Home page
- **GET /login**: Admin login page
- **GET /register**: Admin registration page
- **GET /privacy**: Privacy policy page
- **GET /admin/admin-dashboard**: Admin dashboard (requires authentication)
- **GET /admin/restaurants/new**: Add new restaurant form (requires authentication)
- **GET /admin/restaurants/{id}**: Restaurant settings (requires authentication)
- **GET /admin/restaurants/{id}/reservations**: Reservations page (requires authentication)

## Security

- **BCrypt Password Encoding**: All passwords are securely hashed
- **Role-Based Access Control**: Admins have full access to management features
- **Session Management**: Secure session handling with Spring Security
- **CSRF Protection**: Cross-Site Request Forgery protection enabled

## Database Schema

### Main Entities

1. **Admin**: Represents restaurant owners/administrators
2. **Restaurant**: Contains restaurant information and associated halls
3. **Hall**: Represents a restaurant hall with grid cells
4. **GridCell**: Individual cells in a hall grid with various types
5. **RestaurantTable**: Information about tables (size, type, coordinates)
6. **Reservation**: Customer reservation details


## Known Issues

- Some reservation management features are not fully implemented
- The application is currently admin-focused; customer features are limited
- Error handling and validation can be improved

## AI Integration in Development

**Overview:** Artificial intelligence has been used to support the development of the Smart Restaurant Reservation System. 

**Current Usage:**
- **Code Generation:** AI assisted in creating small, repetitive, and boilerplate code, speeding up development.
- **Debugging and Review:** AI provided code review, identified potential issues, and proposed solutions for bugs.
- **Technical Guidance:** AI was consulted for questions related to implementation logic, feature design, and best practices, helping the development team make informed decisions.


*Last updated: March 2026*
