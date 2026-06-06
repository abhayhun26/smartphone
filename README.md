# Smartphone CRUD API

A robust Spring Boot REST API for managing smartphone products with **JWT Authentication** and role-based access control.

## 📋 Overview

This application provides a complete CRUD (Create, Read, Update, Delete) operation system for smartphone products with enterprise-grade security features including JWT-based authentication and authorization.

**Technology Stack:**
- Java
- Spring Boot
- Spring Security with JWT
- JPA/Hibernate
- H2 Database

## 🎯 Features

### Authentication & Authorization
- **User Registration**: Register new users with email and password
- **User Login**: Authenticate users and receive JWT tokens
- **JWT Token-based Authorization**: Secure API endpoints with JWT tokens
- **Role-based Access Control**: Support for USER roles with extensible role system
- **Password Encryption**: Bcrypt password encoding for security

### Product Management (CRUD Operations)
- **Create Products**: Add new smartphone products with details
- **Read Products**: Retrieve product information
- **Update Products**: Modify existing product details
- **Delete Products**: Remove products from inventory
- **Product Tracking**: Track creation and update timestamps

### Product Attributes
- Product Name
- Brand
- Model
- Price (BigDecimal for precision)
- Description
- Stock Quantity
- Active Status
- Created/Updated Timestamps

## 🏗️ Project Structure

```
src/main/java/com/example/smartphone/
├── entity/
│   ├── Users.java          # User entity with role information
│   └── Products.java       # Product entity with timestamps
├── dto/
│   └── LoginDTO/
│       ├── AuthRequest.java      # Login credentials
│       ├── AuthResponse.java      # Authentication response with token
│       └── RegisterRequest.java   # User registration data
├── service/
│   ├── AuthService.java          # Authentication service interface
│   ├── impl/
│   │   └── AuthServiceImpl.java   # Authentication implementation
│   └── CustomUserDetailsService.java  # Spring Security user details
├── util/
│   ├── JwtUtil.java         # JWT token generation and validation
│   ├── JwtAuthenticationFilter.java  # JWT authentication filter
│   └── SecurityConfig.java   # Spring Security configuration
├── exceptions/
│   ├── ResourceNotFoundExceptioon.java
│   └── UserException.java    # Custom exceptions
├── repository/
│   ├── UserRepository.java   # User data access
│   └── ProductRepository.java # Product data access
└── SmartphoneApplication.java  # Spring Boot entry point
```

## 🔐 Security Configuration

- **CSRF Protection**: Disabled for stateless JWT authentication
- **Session Management**: Stateless (SESSIONLESS) for JWT-based auth
- **Public Endpoints**:
  - `/api/auth/**` - Registration and login endpoints
  - `/h2-console/**` - Database console access

- **Protected Endpoints**:
  - `/api/products/**` - All product operations require authentication

- **JWT Filter**: Custom filter validates tokens before processing requests

## 📝 API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and receive JWT token

### Products (Authenticated)
- `GET /api/products/` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products/` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

## 🛠️ Key Components

### JwtUtil (Token Management)
- Generate JWT tokens with expiration
- Extract username and expiration from tokens
- Validate tokens against user credentials
- HMAC SHA256 signature algorithm

### SecurityConfig
- Configures authentication and authorization
- Defines public and protected endpoints
- Implements stateless session management
- Integrates JWT filter into security chain

### AuthServiceImpl
- User registration with password encryption
- User login with authentication
- JWT token generation for authenticated users
- Validation for duplicate user registration

### CustomUserDetailsService
- Loads user details from database by email
- Creates Spring Security User with authorities
- Integrates with role-based access control

## 🚀 Getting Started

1. **Prerequisites**: Java 11+, Spring Boot 3.x
2. **Database**: Uses H2 in-memory database (configurable)
3. **Configuration**: Set JWT secret and expiration in application.properties
4. **Build & Run**: `mvn spring-boot:run`

## 📦 Dependencies

- Spring Boot Web
- Spring Security
- Spring Data JPA
- JWT (jjwt library)
- Lombok (for reducing boilerplate)
- H2 Database

## 🔑 JWT Configuration

Configure in `application.properties`:
```
jwt.secret=your_secret_key_here
jwt.expiration=3600000  # Token expiration in milliseconds
```

---

**Created**: 2026-06-06  
**Repository**: [abhayhun26/smartphone](https://github.com/abhayhun26/smartphone)
