import Config.IDB;
import Config.PostgresDB;
import Controllers.*;
import Menu.AdminMenu;
import Menu.DoctorMenu;
import Menu.PatientMenu;
import Model.Admin;
import Model.User;
import Services.*;
import Repositories.Impl.*;
import Model.Doctor;
import Model.Patient;

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

        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "user", "hospital_db");

        UserRepositoryImpl userRepository = new UserRepositoryImpl(db);
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(db);
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(db);
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(db);
        MedicineRepositoryImpl medicineRepository = new MedicineRepositoryImpl(db);
        ReportRepositoryImpl reportRepository = new ReportRepositoryImpl(db);
        HospitalRepositoryImpl hospitalRepository = new HospitalRepositoryImpl(db);
        BedRepositoryImpl bedRepository = new BedRepositoryImpl(db);

        AuthService authService = new AuthService(userRepository);
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        MedicineService medicineService = new MedicineService(medicineRepository);
        ReportService reportService = new ReportService(reportRepository);
        HospitalService hospitalService = new HospitalService(hospitalRepository);
        BedService bedService = new BedService(bedRepository);

        AuthController authController = new AuthController(authService);
        DoctorController doctorController = new DoctorController(doctorService);
        PatientController patientController = new PatientController(patientService);
        AppointmentController appointmentController = new AppointmentController(appointmentService);
        MedicineController medicineController = new MedicineController(medicineService);
        ReportController reportController = new ReportController(reportService);
        HospitalController hospitalController = new HospitalController(hospitalService);
        BedController bedController = new BedController(bedService);

        AdminMenu adminMenu = new AdminMenu(scanner, doctorController, patientController, appointmentController,
                medicineController, reportController, hospitalController);

        DoctorMenu doctorMenu = new DoctorMenu(scanner, appointmentController, reportController, medicineController,
                bedController, new Doctor());


        PatientMenu patientMenu = new PatientMenu(scanner, appointmentController, reportController);

        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");
            String action = scanner.nextLine();

            if (action.equalsIgnoreCase("quit")) {
                System.out.println("Exiting the system...");
                break;
            }

            if (action.equalsIgnoreCase("register")) {
                System.out.println("Enter your role (Admin, Doctor, Patient): ");
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
                    user = new Doctor(1, name, email, password, specialization);
                } else if (role.equalsIgnoreCase("Patient")) {
                    System.out.print("Enter Patient Health History: ");
                    String healthHistory = scanner.nextLine();
                    user = new Patient(1, name, email, password, healthHistory);
                } else if (role.equalsIgnoreCase("Admin")) {
                    user = new User(1, name, email, password, "admin");
                } else {
                    System.out.println("Invalid role. Registration failed.");
                    continue;
                }

                if (userRepository.getUserByEmail(email) == null) {
                    userRepository.addUser(user);
                    System.out.println(role + " registered successfully.");
                } else {
                    System.out.println("Email already exists.");
                }
            }

            else if (action.equalsIgnoreCase("login")) {
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                User loggedInUser = authController.login(email, password);

                if (loggedInUser != null) {
                    System.out.println("Login successful! Welcome " + loggedInUser.getName());

                    if (loggedInUser instanceof Admin) {
                        while (true) {
                            adminMenu.displayMenu();
                            System.out.print("Enter your choice: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();
                            adminMenu.handleChoice(choice);
                            if (choice == 15) break;
                        }
                    } else if (loggedInUser instanceof Doctor) {
                        Doctor loggedInDoctor = (Doctor) loggedInUser;
                        while (true) {
                            doctorMenu.displayMenu();
                            System.out.print("Enter your choice: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();
                            doctorMenu.handleChoice(choice);
                            if (choice == 8) break;
                        }
                    } else if (loggedInUser instanceof Patient) {
                        while (true) {
                            patientMenu.displayMenu();
                            System.out.print("Enter your choice: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();
                            patientMenu.handleChoice(choice);
                            if (choice == 3) break;
                        }
                    }
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            }
        }
    }
}
