# Online Shopping System

## Project Overview
This is a web-based Online Shopping System built using Java Servlets and JSP. It allows users to browse products, add them to the cart, and place orders. Admins can manage products and view orders.

## Features
### User Features
- User registration and login (OTP-based authentication)
- Browse and search for products
- Add products to the cart
- Checkout and place orders
- View order history
- Logout functionality

### Admin Features
- Admin login (OTP-based authentication)
- Add, edit, and delete products
- View and manage orders
- Manage stock updates

## Project Setup Instructions

### Prerequisites

1. Install **Java (JDK 8 or higher)**
2. Install **Apache Tomcat (v9 or compatible)**
3. Install **PostgreSQL Database**
4. Install **Maven**
5. Install an IDE (Eclipse/IntelliJ IDEA/NetBeans)

### Steps to Run the Project

#### 1. Clone or Download the Project

Download the ZIP file from GitHub, extract it, and open the project in your preferred IDE.

#### 2. Configure Database

- Open PostgreSQL and create a new database.
- Import the database schema from `database/20210519.sql`.
- Update `src/cn/ecart/connection/DbCon.java` with your PostgreSQL password.

#### 3. Resolve Dependencies

- Open a terminal in the project root directory.
- Run `mvn clean install` to download dependencies and build the project.

#### 4. Configure OTP System

- update OTP generation logic in:

  - `src/cn/techtutorial/servlet/OtpServlet.java`
  - `src/cn/techtutorial/servlet/OtpServlet2.java`

- Implement OTP email sending in:

  - `src/cn/techtutorial/servlet/AdminLoginServlet.java`
  - `src/cn/techtutorial/servlet/LoginServlet.java`

- For security reasons the OTP generation and email sending logic is removed as it discloses the creators email ID and password.

  Even without modifying the OTP logic, the application will run with a fixed OTP for login.

  The OTP for admin login is set to 050304.

  The OTP for user login is set to 210504.5.&#x20;

**5. Deploy the Application**

- Add the project to **Apache Tomcat** in your IDE.
- Run the server and access the web application at `http://localhost:8080/Online-Shopping-System-1/`.

## Technologies Used
- Java Servlets & JSP
- PostgreSQL
- Apache Tomcat
- Maven

## Folder Structure
- `src/cn/techtutorial/servlet/` - Contains servlet files handling user and admin operations.
- `src/cn/ecart/connection/` - Handles database connection.
- `WebContent/` - Contains JSP pages for the front-end.
- `database/` - SQL script for database setup.


### Notes

- Even without modifying the OTP logic, the application will run with a fixed OTP for login.
- Check `pom.xml` for compatibility issues and update dependencies if necessary.

### Troubleshooting

- Ensure PostgreSQL is running and the credentials in `DbCon.java` are correct.
- If dependencies fail to resolve, manually update them in `pom.xml`.
- Check Tomcat logs for deployment errors and fix them accordingly.

---

This guide provides step-by-step instructions to set up and run the **Online Shopping System** project. Modify configurations where necessary to fit your environment.





