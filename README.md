
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

#### From Terminal:
```sh
mvn clean
mvn install
```

#### From IDE (Spring Tool Suite - STS):
- Right-click the project > **Run As > Maven clean**
- Then, **Run As > Maven install**
- Update Maven project if needed: **Maven > Update Project**

---

### 3. Run the Application

#### From Terminal:
```sh
mvn spring-boot:run
```

#### From IDE (STS):
- Right-click the main class > **Run As > Spring Boot App**
- App runs at: `http://localhost:8080/`

---

### 4. Using the Application

- Open browser and go to: `http://localhost:8080/`
- Register as a **Passenger**
- Login with credentials

#### Admin Login:
- **Username:** `admin1`
- **Password:** `adminpass1`

---

### 5. Accessing the H2 Database Console

You can use the H2 Console to view and manage data.

#### Open Console:

- URL: `http://localhost:8080/h2-console`

#### H2 UI Inputs:

| Field              | Value                              |
|--------------------|-------------------------------------|
| **JDBC URL**       | `jdbc:h2:file:./data/railwaydb`     |
| **Username**       | `sa`                                |
| **Password**       | *(leave empty unless set)*          |
| **Driver Class**   | `org.h2.Driver`                     |

✅ Click **Connect** to access your tables like `passenger`, `admin`, `train`, etc.

---
