# Food Ordering System

## Description
A minimal, simple, professional, and easy-to-explain Food Ordering System built using Advanced Java concepts. The application supports standard e-commerce features for customers (browse, cart, place order) and a dashboard for administrators to manage food items and view orders.

This project uses an in-memory repository layer, ensuring a clean MVC architecture that is ready for future database integration.

## Features

### Customer
- Register and Login
- Browse and search food items
- View food details
- Add items to cart and modify quantities
- Place an order
- View order history

### Admin
- Secure Admin login
- View Dashboard (statistics)
- Add, edit, and delete food items
- View customer orders

## Technologies
- Java 17
- Jakarta Servlets 6.0
- JSP (Jakarta Server Pages)
- HTML5 / CSS3
- Apache Tomcat 10.1+
- Maven

## Architecture
```text
Browser
 ↓
JSP (Views)
 ↓
Servlet (Controllers)
 ↓
Service (Business Logic)
 ↓
Repository Interface
 ↓
In-Memory Data (ConcurrentHashMap)
```

## Requirements
- Java 17 JDK
- Apache Maven
- Apache Tomcat 10.1+

## How to Run
1. Install Java 17, Maven, and Tomcat 10.1+.
2. Clone or extract this project folder (`food-ordering-system`).
3. Open a terminal in the project root directory.
4. Build using Maven: `mvn clean package`
5. A `.war` file will be generated in the `target/` directory (`food-ordering-system-1.0-SNAPSHOT.war`).
6. Copy the `.war` file to your Tomcat `webapps/` directory (or deploy via IDE).
7. Start Tomcat.
8. Open your browser and navigate to the application URL (e.g., `http://localhost:8080/food-ordering-system-1.0-SNAPSHOT/`).

## Sample Login
Sample data is pre-populated when the application starts.

**Admin:**
- Email: `admin@food.com`
- Password: `admin123`

**Customer:**
- Email: `customer@food.com`
- Password: `customer123`

---

# FUTURE MYSQL + JDBC INTEGRATION

Currently, the system uses in-memory data structures:
- `InMemoryUserRepository`
- `InMemoryFoodRepository`
- `InMemoryCartRepository`
- `InMemoryOrderRepository`

In the future, these can be seamlessly replaced with:
- `JdbcUserRepository`
- `JdbcFoodRepository`
- `JdbcCartRepository`
- `JdbcOrderRepository`

The Servlet and Service layers will **NOT** need major changes because they depend on the Repository Interfaces, not the concrete implementations.

### Future Architecture
```text
JSP
 ↓
Servlet
 ↓
Service
 ↓
Repository Interface
 ↓
JDBC Repository
 ↓
MySQL
```

### Future Database Tables

#### `users`
- id (INT, PRIMARY KEY, AUTO_INCREMENT)
- name (VARCHAR)
- email (VARCHAR, UNIQUE)
- password (VARCHAR)
- role (VARCHAR: CUSTOMER, ADMIN)

#### `foods`
- id (INT, PRIMARY KEY, AUTO_INCREMENT)
- name (VARCHAR)
- description (TEXT)
- category (VARCHAR)
- price (DECIMAL)
- available (BOOLEAN)

#### `orders`
- id (INT, PRIMARY KEY, AUTO_INCREMENT)
- user_id (INT, FOREIGN KEY)
- total_amount (DECIMAL)
- order_date (DATETIME)
- status (VARCHAR: PLACED, PREPARING, DELIVERED, CANCELLED)

#### `order_items`
- id (INT, PRIMARY KEY, AUTO_INCREMENT)
- order_id (INT, FOREIGN KEY)
- food_id (INT, FOREIGN KEY)
- quantity (INT)
- price (DECIMAL)
