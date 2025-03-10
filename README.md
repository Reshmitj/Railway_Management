# Railway Ticket Management System

This is a Spring Boot application that allows users to register and log in as passengers. Admin access is also available.

## Getting Started

Follow these steps to set up and run the application:

### 1. Prerequisites

- Ensure you have **Java 17** installed on your system.
- You can download and install Java from [this link](https://www.java.com/en/download/help/windows_manual_download.html).

### 2. Download and Set Up the Project

1. **Download the ZIP**
   - Go to the [GitHub Repository](https://github.com/Reshmitj/Railway_Management.git)
   - Click on "Code" and select "Download ZIP"
   - Extract the downloaded ZIP file

2. **Open in an IDE**
   - Open Spring Tool Suite (STS) or any preferred Java IDE
   - Import the project as a **Maven** project

3. **Run Maven Commands** (Before Running the Application)
   - **From Terminal:**
     - Open a terminal inside the project directory and execute the following commands:
       ```sh
       mvn clean
       mvn install
       ```
   - **From IDE (Spring Tool Suite - STS):**
     - Right-click on the project in the **Project Explorer**.
     - Navigate to **Run As > Maven clean** to clean the project.
     - Navigate to **Run As > Maven install** to install dependencies.
     - Right-click on the project and select **Maven > Update Project** to ensure dependencies are correctly resolved.

### 3. Run the Application

#### From Terminal
- Navigate to the project root folder in your terminal.
- Run the following command:
  ```sh
  mvn spring-boot:run
  ```

#### From IDE (Spring Tool Suite - STS)
- Open the **Spring Boot Perspective** in STS.
- Locate the **main class** of the Spring Boot application.
- Right-click and select **Run As > Spring Boot App**.
- The application will start on `http://localhost:8080/`.

### 4. Using the Application

- Navigate to `http://localhost:8080/`
- Register as a **Passenger**
- Log in using your registered credentials

#### Admin Login Credentials
- **Username:** `admin1`
- **Password:** `adminpass1`

---
