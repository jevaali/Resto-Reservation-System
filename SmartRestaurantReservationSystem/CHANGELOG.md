# Changelog

All notable changes to the Smart Restaurant Reservation System will be documented in this file.

## Unreleased

### Added
- User authentication and authorization system
- Admin registration and login functionality
- Restaurant management (create, view)
- Responsive web interface
- H2 database support

### Changed

### Deprecated

### Removed

### Fixed

### Security

## 0.0.1-SNAPSHOT - 2026-03-08

### Added
- Initial project setup with Spring Boot 4.0.3
- Maven project structure
- Basic entity models (Admin, Restaurant, Hall, GridCell, RestaurantTable, Reservation)
- Spring Security configuration for admin authentication
- Thymeleaf templates for frontend
- Basic API endpoints
- H2 database configuration
- Gitignore and project configuration files

### Changed

### Deprecated

### Removed

### Fixed

### Security

## [Unreleased] - TBD

### Planned Features

#### v0.1.0
- [ ] Online reservation system for customers
- [ ] Email notifications for reservations
- [ ] Full reservation management (edit, cancel, confirm)
- [ ] Table availability checking
- [ ] Reservation confirmation emails
- [ ] Improved restaurant search and filtering

### Known Issues

- Reservation management features (edit, cancel, confirm) are not implemented
- The application is currently admin-focused; customer features are limited
- Error handling and validation can be improved
- The UI is not optimized for all screen sizes
- Some features are not fully tested

### Security Concerns

- Currently using H2 database in file mode (not suitable for production)
- No HTTPS configuration
- No account lockout after multiple failed login attempts
- No password recovery system
