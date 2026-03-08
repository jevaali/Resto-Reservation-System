# Quick Start Guide

Get up and running with the Smart Restaurant Reservation System in just a few minutes!

## Prerequisites

- Java 25 or higher installed
- Git installed

## Step 1: Clone the Repository

```bash
git clone <repository-url>
cd SmartRestaurantReservationSystem
```

## Step 2: Build the Project

```bash
mvn clean install
```

## Step 3: Run the Application

```bash
mvn spring-boot:run
```

## Step 4: Access the Application

Open your browser and navigate to:
```
http://localhost:8080
```

## Step 5: Create an Admin Account

1. Click "Register" in the navigation bar
2. Fill in your details:
   - **Name**: Your full name
   - **Email**: Your email address
   - **Password**: Create a strong password
3. Click "Register"
4. You'll be automatically logged in

## Step 6: Add Your First Restaurant

1. On the dashboard, click "Add New Restaurant"
2. Fill in restaurant details:
   - **Name**: Your restaurant's name
   - **Location**: The city or area
   - **Address**: Full address
3. Click "Add Restaurant"

## Step 7: View Your Restaurant

1. Go to the home page (`http://localhost:8080/`)
2. Your restaurant should be listed
3. Click on it to see details


## What's Next?

### For Restaurant Owners

- Configure dining halls
- Experiment with different table layouts
- Invite staff to use the system
- Start taking reservations

### For Customers

- The customer-facing reservation system is coming soon!
- Currently, only restaurant owners can manage the system

## Troubleshooting

### Application Won't Start

- Make sure you're using Java 25 or higher
- Check that Maven is correctly installed
- Verify that the port 8080 is not being used by another application

### Can't Access the Application

- Check that the application is running
- Verify the URL is correct: `http://localhost:8080`
- Check if there are any errors in the terminal


## Important Notes

- This is a development version of the system
- Data is stored locally in a file-based H2 database
- The system is not suitable for production use yet

---

**Congratulations! You've successfully set up your restaurant reservation system.**
