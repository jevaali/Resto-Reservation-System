# Smart Restaurant Reservation System - API Documentation

This document provides detailed information about the RESTful API endpoints available in the Smart Restaurant Reservation System.

## Base URL

All API endpoints are relative to:
```
http://localhost:8080/api
```

## Authentication

Most API endpoints require authentication. The application uses Spring Security with form-based login. Once authenticated, the session is managed via cookies.

## Response Format

All API responses are in JSON format.

### Success Response
```json
{
  "data": "Response content",
  "status": "success",
  "message": "Optional message"
}
```

### Error Response
```json
{
  "message": "Error description",
  "status": "error"
}
```

## Endpoints

### Admin Endpoints

#### Register New Admin
```
POST /admin/register
```

Registers a new restaurant owner/admin.

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "role": "ADMIN"
}
```

**Status Codes:**
- 200 OK: Success
- 400 Bad Request: Email already exists
- 500 Internal Server Error: Server error

#### Admin Login
```
POST /login
```

Authenticates an admin user. This is handled by Spring Security's form login.

**Request Parameters:**
- `username`: Admin's email
- `password`: Admin's password

**Response:**
Redirects to admin dashboard upon success.

**Status Codes:**
- 302 Found: Success (redirect)
- 401 Unauthorized: Invalid credentials

### Restaurant Endpoints

#### Get All Restaurants
```
GET /restaurants
```

Returns a list of all restaurants. Supports optional search.

**Query Parameters:**
- `query` (optional): Search term for restaurant name or location

**Response:**
```json
[
  {
    "id": 1,
    "name": "The Garden Bistro",
    "location": "123 Main St, Cityville",
    "address": "123 Main St, Cityville",
    "halls": []
  },
  {
    "id": 2,
    "name": "Ocean View Restaurant",
    "location": "456 Beach Rd, Seaside",
    "address": "456 Beach Rd, Seaside",
    "halls": []
  }
]
```

**Status Codes:**
- 200 OK: Success

#### Create Restaurant
```
POST /restaurants
```

Creates a new restaurant for the authenticated admin.

**Request Body:**
```json
{
  "name": "Cozy Corner Café",
  "location": "789 Park Ave, Townsburg",
  "address": "789 Park Ave, Townsburg"
}
```

**Response:**
```json
{
  "id": 3,
  "name": "Cozy Corner Café",
  "location": "789 Park Ave, Townsburg",
  "address": "789 Park Ave, Townsburg",
  "halls": []
}
```

**Status Codes:**
- 200 OK: Success
- 401 Unauthorized: Not authenticated
- 500 Internal Server Error: Server error

### Hall Endpoints (To be implemented)

#### Get Halls for Restaurant
```
GET /restaurants/{restaurantId}/halls
```

Returns all halls for a specific restaurant.

**Path Parameters:**
- `restaurantId`: The ID of the restaurant

**Response:**
```json
[
  {
    "id": 1,
    "name": "Main Dining Hall",
    "width": 20,
    "height": 15,
    "gridCells": []
  }
]
```

#### Create Hall
```
POST /restaurants/{restaurantId}/halls
```

Creates a new hall for a restaurant.

**Path Parameters:**
- `restaurantId`: The ID of the restaurant

**Request Body:**
```json
{
  "name": "Patio",
  "width": 15,
  "height": 10
}
```

**Response:**
```json
{
  "id": 2,
  "name": "Patio",
  "width": 15,
  "height": 10,
  "gridCells": []
}
```

#### Update Hall Layout
```
PUT /restaurants/{restaurantId}/halls/{hallId}
```

Updates the hall grid layout.

**Path Parameters:**
- `restaurantId`: The ID of the restaurant
- `hallId`: The ID of the hall

**Request Body:**
```json
{
  "gridCells": [
    {
      "x": 0,
      "y": 0,
      "cellType": "WINDOW"
    },
    {
      "x": 0,
      "y": 1,
      "cellType": "TABLE",
      "tableId": 1
    }
  ]
}
```

### Reservation Endpoints (To be implemented)

#### Get Reservations for Restaurant
```
GET /restaurants/{restaurantId}/reservations
```

Returns all reservations for a specific restaurant.

**Path Parameters:**
- `restaurantId`: The ID of the restaurant

**Query Parameters:**
- `date`: Optional date filter (YYYY-MM-DD)
- `status`: Optional status filter (upcoming, past, cancelled)

**Response:**
```json
[
  {
    "id": 1,
    "customerName": "Jane Smith",
    "customerEmail": "jane@example.com",
    "customerPhone": "123-456-7890",
    "date": "2026-03-15",
    "time": "18:30",
    "guests": 4,
    "tableId": 1,
    "status": "upcoming",
    "notes": "Celebrating anniversary"
  }
]
```

#### Create Reservation
```
POST /restaurants/{restaurantId}/reservations
```

Creates a new reservation.

**Path Parameters:**
- `restaurantId`: The ID of the restaurant

**Request Body:**
```json
{
  "customerName": "Jane Smith",
  "customerEmail": "jane@example.com",
  "customerPhone": "123-456-7890",
  "date": "2026-03-15",
  "time": "18:30",
  "guests": 4,
  "tableId": 1,
  "notes": "Celebrating anniversary"
}
```

**Response:**
```json
{
  "id": 2,
  "customerName": "Jane Smith",
  "customerEmail": "jane@example.com",
  "customerPhone": "123-456-7890",
  "date": "2026-03-15",
  "time": "18:30",
  "guests": 4,
  "tableId": 1,
  "status": "upcoming",
  "notes": "Celebrating anniversary"
}
```

#### Cancel Reservation
```
PUT /restaurants/{restaurantId}/reservations/{reservationId}/cancel
```

Cancels a reservation.

**Path Parameters:**
- `restaurantId`: The ID of the restaurant
- `reservationId`: The ID of the reservation

**Response:**
```json
{
  "id": 2,
  "customerName": "Jane Smith",
  "customerEmail": "jane@example.com",
  "customerPhone": "123-456-7890",
  "date": "2026-03-15",
  "time": "18:30",
  "guests": 4,
  "tableId": 1,
  "status": "cancelled",
  "notes": "Celebrating anniversary"
}
```

## Table Endpoints (To be implemented)

#### Get Tables for Hall
```
GET /halls/{hallId}/tables
```

Returns all tables in a specific hall.

**Path Parameters:**
- `hallId`: The ID of the hall

**Response:**
```json
[
  {
    "id": 1,
    "tableSize": "4x4",
    "x": 5,
    "y": 3,
    "hallId": 1
  }
]
```

#### Create Table
```
POST /halls/{hallId}/tables
```

Creates a new table in a hall.

**Path Parameters:**
- `hallId`: The ID of the hall

**Request Body:**
```json
{
  "tableSize": "4x6",
  "x": 8,
  "y": 5
}
```

**Response:**
```json
{
  "id": 2,
  "tableSize": "4x6",
  "x": 8,
  "y": 5,
  "hallId": 1
}
```

## Error Codes

### Client Errors (4xx)

- **400 Bad Request**: Invalid request parameters or body
- **401 Unauthorized**: Authentication required or invalid credentials
- **403 Forbidden**: Access denied
- **404 Not Found**: Resource not found
- **405 Method Not Allowed**: HTTP method not supported

### Server Errors (5xx)

- **500 Internal Server Error**: General server error
- **501 Not Implemented**: Feature not yet implemented

## Data Models

### Admin
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "password": "encrypted-password",
  "role": "ADMIN",
  "restaurants": []
}
```

### Restaurant
```json
{
  "id": 1,
  "name": "The Garden Bistro",
  "location": "123 Main St, Cityville",
  "address": "123 Main St, Cityville",
  "halls": []
}
```

### Hall
```json
{
  "id": 1,
  "name": "Main Dining Hall",
  "width": 20,
  "height": 15,
  "gridCells": []
}
```

### GridCell
```json
{
  "x": 0,
  "y": 1,
  "cellType": "TABLE",
  "tableId": 1
}
```

**Cell Type Options:**
- EMPTY: Empty space
- BLOCKED: Blocked area (walls, columns, etc.)
- WINDOW: Window seating
- TERRACE: Terrace/seating area
- CHILDREN: Children-friendly seating
- PRIVATE: Private dining area
- TABLE: Table (requires tableId)

### RestaurantTable
```json
{
  "id": 1,
  "tableSize": "4x4",
  "x": 5,
  "y": 3,
  "hallId": 1
}
```

**Table Size Options:**
- 4x4: 2-4 people
- 4x6: 4-6 people
- 4x8: 6-8 people

### Reservation
```json
{
  "id": 1,
  "customerName": "Jane Smith",
  "customerEmail": "jane@example.com",
  "customerPhone": "123-456-7890",
  "date": "2026-03-15",
  "time": "18:30",
  "guests": 4,
  "tableId": 1,
  "status": "upcoming",
  "notes": "Celebrating anniversary"
}
```

**Status Options:**
- upcoming: Reservation is in the future
- past: Reservation has already occurred
- cancelled: Reservation has been cancelled
- confirmed: Reservation has been confirmed

## Examples Using cURL

### Register Admin
```bash
curl -X POST http://localhost:8080/api/admin/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com", 
    "password": "password123"
  }'
```

### Get All Restaurants
```bash
curl -X GET "http://localhost:8080/api/restaurants"
```

### Search Restaurants
```bash
curl -X GET "http://localhost:8080/api/restaurants?query=garden"
```

### Create Restaurant (Authenticated)
```bash
curl -X POST http://localhost:8080/api/restaurants \
  -H "Content-Type: application/json" \
  -b "JSESSIONID=<your-session-id>" \
  -d '{
    "name": "Cozy Corner Café",
    "location": "789 Park Ave, Townsburg",
    "address": "789 Park Ave, Townsburg"
  }'
```

## Postman Collection

A Postman collection for testing the API is available upon request.

## Development Notes

### API Versioning

Currently, there's no API versioning implemented. Future versions may use URI versioning (e.g., `/api/v1/`).

### Rate Limiting

No rate limiting is implemented in this version.

### CORS

Cross-Origin Resource Sharing (CORS) is not configured. The API is intended to be used from the same origin.

### Logging

API requests are logged using Spring Boot's default logging configuration.

## Future Enhancements

- API versioning
- Rate limiting
- CORS configuration
- Advanced filtering and sorting
- Pagination
- WebSocket notifications for real-time updates
- GraphQL API
