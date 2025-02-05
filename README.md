# Hospital Management System

## Description

The **Hospital Management System** is a Java-based console application designed to manage various hospital operations. The system includes user management, appointment booking, medical reports, and more. It supports different roles including **Admin**, **Doctor**, **Nurse**, **Patient**, and **Pharmacist**.

### Features:
- **User Management**: Allows sign-up, login, and authentication for multiple roles.
- **Appointment Management**: Enables patients to book, view, and cancel appointments.
- **Report Generation**: Doctors can generate medical reports.
- **Hospital Management**: Admin can manage doctors, patients, hospitals, beds, and other resources.
- **Database Integration**: Uses PostgreSQL for data storage.
- **Role-based Access**: Different functionalities based on the user's role.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/shaminabd/groupProject3.git
Set up PostgreSQL:

Install PostgreSQL and create a database named hospital_db.
Ensure the PostgreSQL server is running on localhost:5432.
Dependencies:

Java 8+ or higher.
PostgreSQL JDBC Driver (included in the project).
Database Initialization:

The database will initialize automatically when you run the app.
Tables such as users, doctors, patients, and appointments will be created on the first run.
Usage
Run the Application:
    javac Main.java
    java Main
After the application starts, you will be prompted to register or login.

Admins can manage doctors, patients, appointments, and hospitals.
Doctors can view and generate medical reports.
Patients can book and view appointments.
Contributing
To contribute to this project:

Fork the repository.
Create a new branch (git checkout -b feature-name).
Commit your changes (git commit -m 'Add feature').
Push to the branch (git push origin feature-name).
Open a pull request.
License
This project is licensed under the MIT License.

Authors
Shynggys Abdullayev – Initial work – GitHub
Baimen Ali
Zukhra Kabyshova
Sanat Bogenbayev
Acknowledgments
PostgreSQL for data storage.
Java for application logic and user interface.
