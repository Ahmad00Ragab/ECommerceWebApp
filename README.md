# Shoesly E-Commerce Application

Shoesly is an e-commerce web application designed for seamless shopping of shoes and related items. The project implements core Java EE technologies such as Servlets and Hibernate ORM for managing the application's backend.

## Features
- User authentication and session management.
- Shopping cart functionality with dynamic item count in the header.
- Checkout flow that handles stock validation and user credit checks.
- Seamless user interface using JSP and Bootstrap.
- Dynamic interactions powered by AJAX for certain actions.

## Technologies Used
- **Java** (JDK 22)
- **Servlets** for request handling and user authentication.
- **Hibernate** ORM for database persistence.
- **JSP** (JavaServer Pages) for server-side rendering.
- **AJAX** for dynamic content updates.
- **JUnit** with **Mockito** for unit testing.
- **MySQL** for the relational database.
- **Tomcat** for the web server (with Smart Tomcat plugin for deployment).
- **JSTL** (JSP Standard Tag Library) for cleaner JSP code.
- **Bootstrap** for responsive design and front-end styling.
- **Maven** for project dependency management and builds.

## Developers

<ul style="list-style-type: disc; display: inline;">
  <li style="display: inline; margin-right: 10px;"><b>Antony Boutros</b></li>
  <li style="display: inline; margin-right: 10px;"><b>Abdulmajeed Mohamed</b></li>
  <li style="display: inline;"><b>Ahmed Haroun</b></li>
</ul>


## Installation and Setup

### Prerequisites
- Java Development Kit (JDK 22)
- Apache Tomcat 9+
- MySQL
- Maven
- An IDE like IntelliJ IDEA (or your preferred one with Tomcat support)

### Steps to Run Locally

1. **Clone the repository**:
   ```bash
   https://github.com/Ahmad00Ragab/ECommerceWebApp.git
2. **Configure the Database:**
   - Install MySQL and create a database named `shoesly_db`.
   - Update your `hibernate.cfg.xml` file with your database credentials (username and password).

3. **Build the Project:**
   - Use Maven to clean and build the project:
     ```bash
     mvn clean install
     ```

4. **Deploy on Tomcat:**
   - Ensure that you have Apache Tomcat installed and set up.
   - Copy the generated `.war` file from the `target/` directory to the `webapps/` directory of your Tomcat installation.
   - Start or restart the Tomcat server.

5. **Run the Application:**
   - Access the Shoesly application by visiting `http://localhost:8080/shoesly/` in your web browser.

6. **Database Schema:**
   - The application automatically generates the necessary database schema based on the Hibernate configuration.

## Usage

- Navigate to the homepage and browse available products.
- Register or log in to add items to your cart.
- Proceed to checkout when ready to place your order.

## Testing

- Unit tests are written using **JUnit** and **Mockito**.
- To run the tests, use the following Maven command:
   ```bash
   mvn test


![Classes](https://github.com/user-attachments/assets/8ef0a6cd-4f85-4a34-9330-19ac3dca7aae)
