# Smart Restaurant Reservation System - Development Guide

## Database Configuration

### H2 Database

The application uses H2 database in file mode. The database is automatically created when the application starts.

**File Location**: `data/reservationdb.mv.db`

**H2 Console**: `http://localhost:8080/h2-console`

**Credentials**:
- JDBC URL: `jdbc:h2:file:./data/reservationdb`
- Username: `sa`
- Password: (empty)

### Database Schema

The schema is automatically created and updated by Hibernate when the application starts. The initial schema includes:

- `admins` - Admin user accounts
- `restaurants` - Restaurant information
- `halls` - Restaurant dining halls
- `grid_cells` - Individual cells in a hall
- `restaurant_tables` - Table information
- `reservations` - Customer reservations

## Project Structure

### Source Code

```
src/
├── main/
│   ├── java/com/example/smartrestaurantreservationsystem/
│   │   ├── SmartRestaurantReservationSystemApplication.java
│   │   ├── config/                    # Configuration files
│   │   │   ├── PasswordEncoderConfig.java
│   │   │   └── SecurityConfig.java
│   │   ├── controllers/              # REST and web controllers
│   │   │   ├── AdminController.java
│   │   │   ├── AdminPageController.java
│   │   │   ├── PageController.java
│   │   │   └── RestaurantController.java
│   │   ├── DTO/                      # Data Transfer Objects
│   │   │   ├── AdminDTO.java
│   │   │   └── LoginRequest.java
│   │   ├── handler/                  # Authentication handlers
│   │   │   ├── CustomAuthenticationSuccessHandler.java
│   │   │   └── CustomLogoutSuccessHandler.java
│   │   ├── model/                    # Entity models
│   │   │   ├── Admin.java
│   │   │   ├── GridCell.java
│   │   │   ├── Hall.java
│   │   │   ├── Reservation.java
│   │   │   ├── Restaurant.java
│   │   │   ├── RestaurantTable.java
│   │   │   ├── base/
│   │   │   │   └── BaseEntity.java
│   │   │   └── enums/
│   │   │       └── CellType.java
│   │   ├── repositories/             # JPA repositories
│   │   │   ├── AdminRepository.java
│   │   │   ├── GridCellRepository.java
│   │   │   ├── HallRepository.java
│   │   │   ├── ReservationRepository.java
│   │   │   ├── RestaurantRepository.java
│   │   │   └── RestaurantTableRepository.java
│   │   └── services/                 # Business logic services
│   │       ├── AdminService.java
│   │       ├── CustomUserDetailsService.java
│   │       ├── HallService.java
│   │       ├── ReservationService.java
│   │       ├── RestaurantService.java
│   │       ├── TableAvailabilityService.java
│   │       ├── TableRecommendationService.java
│   │       └── TableService.java
│   └── resources/
│       ├── application.properties    # Spring configuration
│       ├── static/                   # Static assets
│       │   ├── css/                  # Stylesheets
│       │   ├── js/                   # JavaScript files
│       │   │   ├── api/              # API services
│       │   │   ├── services/         # Service layer for UI
│       │   │   └── ui/               # UI components
│       │   └── main.js
│       └── templates/                # Thymeleaf templates
└── test/                             # Test files
```