# 📏 Quantity Measurement Application (UC1–UC17)

---

## 🚀 Introduction

The **Quantity Measurement Application** is a Java-based project that evolved from basic object-oriented programming concepts into a **full-fledged Spring Boot REST API**.

It allows users to:

* Compare quantities
* Convert units
* Perform arithmetic operations
* Handle multiple measurement types
* Persist data using JPA
* Access APIs via REST endpoints

By UC17, the application is fully integrated with **Spring Boot, Database, Swagger, and Testing frameworks**.

---

## 📁 Folder Structure

```
quantitymeasurement/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/apps/quantitymeasurement/
 │   │   │   ├── controller/
 │   │   │   ├── service/
 │   │   │   ├── repository/
 │   │   │   ├── model/
 │   │   │   ├── dto/
 │   │   │   ├── exception/
 │   │   │   └── config/
 │   │   │
 │   │   └── resources/
 │   │       └── application.properties
 │   │
 │   ├── test/
 │   │   ├── controller/
 │   │   └── integration/
 │
 └── pom.xml
```

---

## 📚 Use Case Descriptions

### 🔹 UC1 – Refactor Code

* Refactored initial code for better readability and structure
* Introduced proper class design

### 🔹 UC2 – Equality Check

* Compare two quantities for equality
* Ensure correct object comparison

### 🔹 UC3 – Handle Null Values

* Properly handle null inputs
* Avoid NullPointerException

### 🔹 UC4 – Compare Same Units

* Compare quantities with the same unit
* Validate equality based on value

---

### 🔹 UC5 – Unit Conversion (Feet & Inches)

* Convert inches to feet
* Compare different units of same type

### 🔹 UC6 – Add More Units

* Introduced yard conversion
* Support multiple length units

### 🔹 UC7 – Compare Different Units

* Compare quantities after conversion
* Ensure correct base unit usage

### 🔹 UC8 – Extend Conversion Logic

* Centralized conversion mechanism
* Improved flexibility

---

### 🔹 UC9 – Add Quantities

* Perform addition of quantities
* Return correct result after conversion

### 🔹 UC10 – Arithmetic Operations

* Support arithmetic operations across units
* Maintain precision

### 🔹 UC11 – Multi-unit Arithmetic

* Handle operations across different units
* Convert before calculation

### 🔹 UC12 – Improved Design

* Introduced better abstraction
* Cleaner service-based logic

---

### 🔹 UC13 – Multiple Measurement Types

* Added support for:

  * Length
  * Weight
  * Volume
  * Temperature

### 🔹 UC14 – Error Handling

* Handle invalid operations
* Prevent cross-measurement calculations

### 🔹 UC15 – Operation History

* Store history of operations
* Track results and errors

### 🔹 UC16 – Enhanced Features

* Improved validation
* Structured error responses

---

### 🔹 UC17 – Spring Boot Integration

* Converted application into REST API
* Implemented:

  * Controllers (`@RestController`)
  * Service layer (`@Service`)
  * Repository layer (`@Repository`)
* Added:

  * JPA (database persistence)
  * Global Exception Handling (`@ControllerAdvice`)
  * Swagger API documentation
  * Unit & Integration Testing
  * H2 Database support

---

## ⚙️ Features

* Compare quantities
* Add quantities
* Convert units
* Multi-measurement support
* REST API endpoints
* Global exception handling
* Database integration
* Testing (JUnit)

---


## 🔑 Key Concepts Learned

* Object-Oriented Design
* Unit Conversion Logic
* REST API Development
* Spring Boot Architecture
* Dependency Injection
* JPA & Hibernate
* Exception Handling
* Testing (Unit + Integration)
* Swagger Documentation

---

## ✅ Final Outcome

* Fully functional REST API
* Supports multiple measurement types
* Robust error handling
* Database persistence
* API documentation
* Well-tested application

---
