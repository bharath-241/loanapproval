# Loan Approval System

## Project Description
Loan Approval System is a Spring Boot REST API project used to manage loan applications.
It supports creating, verifying, approving, rejecting, viewing, and deleting loan applications.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Swagger UI
- Git & GitHub

## Features
- Create Loan Application
- Get All Loan Applications
- Verify Loan
- Approve Loan
- Reject Loan
- Delete Loan
- DTO Validation
- Global Exception Handling
- Swagger API Testing

## API Endpoints

### Create Loan
POST /api/loans

### Get All Loans
GET /api/loans

### Verify Loan
PUT /api/loans/{id}/verify

### Approve Loan
PUT /api/loans/{id}/approve

### Reject Loan
PUT /api/loans/{id}/reject

### Delete Loan
DELETE /api/loans/{id}

## Author
Bharath Kumar Malli
