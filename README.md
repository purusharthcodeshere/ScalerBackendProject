# DesiBazaar: E-Commerce Website

This is a **Java Spring Boot** backend project for an **E-Commerce platform**, designed for high performance and scalability. The project integrates multiple **cloud services**, follows **best coding practices**, and ensures **efficient API performance**.

## 🚀 Features & Tech Stack

### 📌 Cloud Services & Infrastructure:
- **AWS RDS**: Used for database management.
- **AWS EC2**: Deployed for scaling and handling traffic efficiently.
- **Redis Caching**: Optimized API calls by **85-95%**, reducing response time significantly.

### 💳 Payment Gateway Integration:
- **RazorPay & Stripe** integrated for seamless payments.
- Implemented **callbacks & webhooks** to handle payment confirmations.

### 🔗 Third-Party API Integrations:
- Integrated external APIs for fetching product data.

### 🛠 Tech Stack:
- **Java, Spring Boot, Hibernate, JPA**
- **MySQL (RDS), Redis (Cache)**
- **AWS EC2, RDS**
- **Stripe, RazorPay**

## 🎯 Design & Best Practices

### ✅ SOLID Principles:
The project follows **SOLID principles** to ensure maintainability and scalability:
- **S**: Single Responsibility Principle – Each class (e.g., `ProductService`, `CategoryService`) has a **single well-defined responsibility**.
- **O**: Open-Closed Principle – Implemented **interfaces and abstraction** (`PaymentService`, `ProductService`).
- **L**: Liskov Substitution Principle – Used **interface-driven design** for Payment implementations (`StripePaymentGatewayImplementation`).
- **I**: Interface Segregation Principle – Segmented service layer interfaces (`ProductService`, `CategoryService`).
- **D**: Dependency Inversion Principle – Used **constructor-based dependency injection** in controllers.

### 🛠 Exception Handling:
- Implemented **custom exception classes** like:
  - `ProductNotFoundException`
  - `CategoryNotFoundException`
- Centralized exception handling using **Controller Advice** (`ControllerAdvice.java`).

## 📂 Project Structure
```
src/main/java/com/scaler/backendproject
├── advice/
│   └── ControllerAdvice.java   # Global Exception Handling
├── configs/
│   ├── RedisTemplateConfig.java  # Redis Config for Caching
│   ├── AuditConfig.java          # Entity Auditing Config
├── controller/
│   ├── ProductController.java    # API for Products
│   ├── CategoryController.java   # API for Categories
│   ├── PaymentController.java    # API for Payments & Webhooks
├── dto/
│   ├── PaymentRequestDTO.java    # DTO for Payment Gateway
│   ├── ErrorDTO.java             # DTO for Error Messages
├── exceptions/
│   ├── ProductNotFoundException.java
│   ├── CategoryNotFoundException.java
├── models/
│   ├── Product.java   # Product Entity
│   ├── Category.java  # Category Entity
│   ├── BaseModel.java # Base Entity with Auditing
├── repository/
│   ├── ProductRepository.java    # JPA Repository for Products
│   ├── CategoryRepository.java   # JPA Repository for Categories
├── service/
│   ├── ProductService.java       # Service Interface
│   ├── CategoryService.java      # Service Interface
│   ├── StripePaymentGatewayImplementation.java  # Payment Integration with Stripe
│   ├── PaymentService.java       # Payment Service Interface
```

## 🚀 How to Run Locally
1. **Clone the repository**  
   ```sh
   git clone https://github.com/purusharthcodeshere/ScalerBackendProject.git
   cd ScalerBackendProject
   ```
2. **Set up database** using AWS RDS or MySQL locally.
3. **Configure Redis** (Ensure Redis is running).
4. **Run the application**  
   ```sh
   mvn spring-boot:run
   ```
5. **Access API** via `http://localhost:8080`

## 📌 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/product` | Create a new product |
| `GET` | `/product/{id}` | Get a product by ID |
| `GET` | `/products?pageNumber=1&pageSize=10&fieldName=price` | Get paginated products |
| `POST` | `/payments` | Create a payment link |
| `POST` | `/webhook` | Handle payment webhooks |

---
