import Config.DatabaseInitializer;
import Controllers.*;
import Services.*;
import Repositories.Impl.*;
import Model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Initializing Database...");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        DatabaseInitializer.initializeDatabase();
        System.out.println("Hospital Management System Starting...");

        // Repositories
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl();
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl();
        ReportRepositoryImpl reportRepository = new ReportRepositoryImpl();
        MedicineRepositoryImpl medicineRepository = new MedicineRepositoryImpl();
        BedRepositoryImpl bedRepository = new BedRepositoryImpl();
        HospitalRepositoryImpl hospitalRepository = new HospitalRepositoryImpl();

        // Services
        AuthService authService = new AuthService(userRepository);
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        ReportService reportService = new ReportService(reportRepository);
        MedicineService medicineService = new MedicineService(medicineRepository);
        BedService bedService = new BedService(bedRepository);
        HospitalService hospitalService = new HospitalService(hospitalRepository);

        // Controllers
        AuthController authController = new AuthController(authService);
        DoctorController doctorController = new DoctorController(doctorService);
        PatientController patientController = new PatientController(patientService);
        AppointmentController appointmentController = new AppointmentController(appointmentService);
        ReportController reportController = new ReportController(reportService);
        MedicineController medicineController = new MedicineController(medicineService);
        BedController bedController = new BedController(bedService);
        HospitalController hospitalController = new HospitalController(hospitalService);

        // Initialize Admin if not exists
        User admin = userRepository.getUserByEmail("admin@hospital.com");

        if (admin == null) {
            System.out.println("Admin not found. Registering admin...");
            System.out.print("Enter Admin Email: ");
            String adminEmail = scanner.nextLine();
            System.out.print("Enter Admin Password: ");
            String adminPassword = scanner.nextLine();
            admin = new Admin(1, "Admin", adminEmail, adminPassword, "admin");
            userRepository.addUser(admin);
            System.out.println("Admin registered successfully.");
        } else {
            System.out.println("Admin already exists. Proceeding...");
        }

        // Main loop for registration/login
        while (true) {
            System.out.println("Do you want to register, login, or quit? (register/login/quit): ");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("quit")) {
                System.out.println("Exiting the system...");
                break;
            }

            if (action.equalsIgnoreCase("register")) {
                System.out.println("Enter your role (Admin, Doctor, Nurse, Patient, Pharmacist): ");
                String role = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();

                User user = null;

                if (role.equalsIgnoreCase("Doctor")) {
                    System.out.print("Enter Doctor Specialization: ");
                    String specialization = scanner.nextLine();
                    user = new Doctor(1, name, email, password, specialization); // role is now set in constructor
                } else if (role.equalsIgnoreCase("Patient")) {
                    System.out.print("Enter Patient Health History: ");
                    String healthHistory = scanner.nextLine();
                    user = new Patient(1, name, email, password, healthHistory); // role is now set in constructor
                } else if (role.equalsIgnoreCase("Nurse")) {
                    user = new Nurse(1, name, email, password); // role is now set in constructor
                } else if (role.equalsIgnoreCase("Pharmacist")) {
                    user = new Pharmacist(1, name, email, password); // role is now set in constructor
                }

                if (user != null) {
                    boolean isRegistered = authController.signUp(user, email);  // Removed redundant role argument
                    if (isRegistered) {
                        System.out.println(role + " registered successfully.");
                    } else {
                        System.out.println(role + " already exists.");
                    }
                } else {
                    System.out.println("Invalid role selected. Please choose a valid role.");
                }

            }
            } else if (action.equalsIgnoreCase("login")) {
                // Login process
                User loggedInUser = null;

                while (loggedInUser == null) {
                    System.out.println("\nLogin to Hospital Management System:");
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    loggedInUser = authController.login(email, password);
                    if (loggedInUser == null) {
                        System.out.println("Invalid credentials. Try again.");
                    }
                }
                // Check user role after login
                if (loggedInUser instanceof Admin) {
                    System.out.println("Welcome, Admin! You are logged in as Admin.");
                } else if (loggedInUser instanceof Doctor) {
                    System.out.println("Welcome, Doctor! You are logged in as Doctor.");
                } else if (loggedInUser instanceof Patient) {
                    System.out.println("Welcome, Patient! You are logged in as Patient.");
                } else if (loggedInUser instanceof Nurse) {
                    System.out.println("Welcome, Nurse! You are logged in as Nurse.");
                } else if (loggedInUser instanceof Pharmacist) {
                    System.out.println("Welcome, Pharmacist! You are logged in as Pharmacist.");
                }
                // Main Menu for Logged-in User
                while (true) {
                    System.out.println("\nHospital Management System Menu:");
                    if (loggedInUser instanceof Admin) {
                        System.out.println("1. View Doctors");
                        System.out.println("2. Remove Doctor");
                        System.out.println("3. View Patients");
                        System.out.println("4. Remove Patient");
                        System.out.println("5. View Appointments");
                        System.out.println("6. Remove Appointment");
                        System.out.println("7. View Medicines");
                        System.out.println("8. Remove Medicine");
                        System.out.println("9. Generate Report (Receipt)");
                        System.out.println("10. Add Hospital");
                        System.out.println("11. Log Out");
                    } else if (loggedInUser instanceof Doctor) {
                        System.out.println("1. View Appointments");
                        System.out.println("2. Create Report");
                        System.out.println("3. Log Out");
                    } else if (loggedInUser instanceof Nurse) {
                        System.out.println("1. Allocate Bed");
                        System.out.println("2. Log Out");
                    } else if (loggedInUser instanceof Pharmacist) {
                        System.out.println("1. Add Medicine");
                        System.out.println("2. Log Out");
                    } else if (loggedInUser instanceof Patient) {
                        System.out.println("1. Book Appointment");
                        System.out.println("2. View Appointment");
                        System.out.println("3. Log Out");
                    }
                    System.out.println("12. Exit System");

                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Clear the buffer

                    switch (choice) {
                        case 1:
                            if (loggedInUser instanceof Admin) {
                                doctorController.viewDoctors();
                            } else if (loggedInUser instanceof Doctor) {
                                appointmentController.viewAppointments();
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 2:
                            if (loggedInUser instanceof Admin) {
                                System.out.print("Enter Doctor ID to remove: ");
                                int doctorId = scanner.nextInt();
                                scanner.nextLine();  // Clear the buffer
                                doctorController.removeDoctor(doctorId);
                            } else if (loggedInUser instanceof Doctor) {
                                System.out.print("Enter Report Details: ");
                                String reportDetails = scanner.nextLine();
                                reportController.createReport(new Report(1, 1, 1, reportDetails));
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 3:
                            if (loggedInUser instanceof Admin) {
                                patientController.viewPatients();
                            } else if (loggedInUser instanceof Patient) {
                                appointmentController.viewAppointments();
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 4:
                            if (loggedInUser instanceof Admin) {
                                System.out.print("Enter Patient ID to remove: ");
                                int patientId = scanner.nextInt();
                                scanner.nextLine();  // Clear the buffer
                                patientController.removePatient(patientId);
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 5:
                            if (loggedInUser instanceof Admin) {
                                appointmentController.viewAppointments();
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 6:
                            if (loggedInUser instanceof Admin) {
                                System.out.print("Enter Appointment ID to remove: ");
                                int appointmentId = scanner.nextInt();
                                scanner.nextLine();  // Clear the buffer
                                appointmentController.removeAppointment(appointmentId);
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 7:
                            if (loggedInUser instanceof Admin) {
                                medicineController.viewMedicines();
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 8:
                            if (loggedInUser instanceof Admin) {
                                System.out.print("Enter Medicine ID to remove: ");
                                int medicineId = scanner.nextInt();
                                scanner.nextLine();  // Clear the buffer
                                medicineController.removeMedicine(medicineId);
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 9:
                            if (loggedInUser instanceof Admin) {
                                System.out.println("Generating a custom report (receipt)... ");
                                System.out.print("Enter Report Details: ");
                                String reportDetails = scanner.nextLine();
                                reportController.createReport(new Report(1, 1, 1, reportDetails));
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 10:
                            if (loggedInUser instanceof Admin) {
                                System.out.println("Adding a custom hospital...");
                                System.out.print("Enter Hospital Name: ");
                                String hospitalName = scanner.nextLine();
                                System.out.print("Enter Hospital Location: ");
                                String location = scanner.nextLine();
                                hospitalController.addHospital(new Hospital(1, hospitalName, location));
                                System.out.println("Custom Hospital Added Successfully.");
                            } else {
                                System.out.println("Access Denied.");
                            }
                            break;
                        case 11:
                            System.out.println("Logging out...");
                            loggedInUser = null; // Logout the user
                            break;
                        case 12:
                            System.out.println("Exiting the system...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid choice, please try again.");
                    }

                    if (loggedInUser == null) {
                        break;  // Exit the menu after logging out
                    }
                }
            }
        }
    }
}
