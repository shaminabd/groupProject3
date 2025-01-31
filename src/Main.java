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


        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl();
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl();
        ReportRepositoryImpl reportRepository = new ReportRepositoryImpl();
        MedicineRepositoryImpl medicineRepository = new MedicineRepositoryImpl();
        BedRepositoryImpl bedRepository = new BedRepositoryImpl();
        HospitalRepositoryImpl hospitalRepository = new HospitalRepositoryImpl();


        AuthService authService = new AuthService(userRepository);
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        ReportService reportService = new ReportService(reportRepository);
        MedicineService medicineService = new MedicineService(medicineRepository);
        BedService bedService = new BedService(bedRepository);
        HospitalService hospitalService = new HospitalService(hospitalRepository);


        AuthController authController = new AuthController(authService);
        DoctorController doctorController = new DoctorController(doctorService);
        PatientController patientController = new PatientController(patientService);
        AppointmentController appointmentController = new AppointmentController(appointmentService);
        ReportController reportController = new ReportController(reportService);
        MedicineController medicineController = new MedicineController(medicineService);
        BedController bedController = new BedController(bedService);
        HospitalController hospitalController = new HospitalController(hospitalService);


        User admin = userRepository.getUserByEmail("admin@hospital.com");

        if (admin == null) {
            System.out.println("Admin not found. Registering admin...");


            System.out.print("Enter Admin Email: ");
            String adminEmail = scanner.nextLine();
            System.out.print("Enter Admin Password: ");
            String adminPassword = scanner.nextLine();
            admin = new Admin(1, "Admin", adminEmail, adminPassword);
            userRepository.addUser(admin);
            System.out.println("Admin registered successfully.");
        } else {
            System.out.println("Admin already exists. Proceeding...");
        }


        System.out.println("Do you want to register or login? (register/login): ");
        String action = scanner.nextLine();

        if (action.equalsIgnoreCase("register")) {
            System.out.println("Enter your role (Admin, Doctor, Nurse, Patient, Pharmacist): ");
            String role = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();


            if (role.equalsIgnoreCase("Doctor")) {
                System.out.print("Enter Doctor Specialization: ");
                String specialization = scanner.nextLine();
                User user = new Doctor(1, name, email, password, specialization);
                boolean isRegistered = authController.signUp(user, role);
                if (isRegistered) {
                    System.out.println("Doctor registered successfully.");
                } else {
                    System.out.println("Doctor already exists.");
                }
            } else if (role.equalsIgnoreCase("Patient")) {
                System.out.print("Enter Patient Health History: ");
                String healthHistory = scanner.nextLine();
                User user = new Patient(1, name, email, password, healthHistory);
                boolean isRegistered = authController.signUp(user, role);
                if (isRegistered) {
                    System.out.println("Patient registered successfully.");
                } else {
                    System.out.println("Patient already exists.");
                }
            } else if (role.equalsIgnoreCase("Nurse")) {

                User user = new Nurse(1, name, email, password);
                boolean isRegistered = authController.signUp(user, role);
                if (isRegistered) {
                    System.out.println("Nurse registered successfully.");
                } else {
                    System.out.println("Nurse already exists.");
                }
            } else if (role.equalsIgnoreCase("Pharmacist")) {
                User user = new Pharmacist(1, name, email, password);
                boolean isRegistered = authController.signUp(user, role);
                if (isRegistered) {
                    System.out.println("Pharmacist registered successfully.");
                } else {
                    System.out.println("Pharmacist already exists.");
                }
            } else {
                System.out.println("Invalid role selected. Please choose a valid role.");
            }
        } else if (action.equalsIgnoreCase("login")) {

            User loggedInUser = null;

            while (loggedInUser == null) {
                System.out.println("\nLogin to Hospital Management System:");
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();
                loggedInUser = authController.login(email, password) ? userRepository.getUserByEmail(email) : null;
                if (loggedInUser == null) {
                    System.out.println("Invalid credentials. Try again.");
                }
            }
            System.out.println("Welcome, " + loggedInUser.getName() + "! You are logged in as " + loggedInUser.getClass().getSimpleName());


            while (true) {
                System.out.println("\nHospital Management System Menu:");

                if (loggedInUser instanceof Admin) {
                    System.out.println("1. Register Doctor (Admin Only)");
                    System.out.println("7. Add Hospital (Admin Only)");
                }
                System.out.println("2. Register Patient");
                System.out.println("3. Book Appointment");
                if (loggedInUser instanceof Doctor) {
                    System.out.println("4. Create Custom Report (Doctors Only)");
                }
                if (loggedInUser instanceof Pharmacist) {
                    System.out.println("5. Add Custom Medicine (Pharmacists Only)");
                }
                if (loggedInUser instanceof Nurse) {
                    System.out.println("6. Allocate Custom Bed (Nurses Only)");
                }
                System.out.println("8. View Bill & Make Payment");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        if (loggedInUser instanceof Admin) {
                            System.out.print("Enter Doctor Name: ");
                            String doctorName = scanner.nextLine();
                            System.out.print("Enter Doctor Specialization: ");
                            String specialization = scanner.nextLine();
                            doctorController.registerDoctor(new Doctor(1, doctorName, "email@hospital.com", "pass123", specialization));
                            System.out.println("Doctor Registered Successfully.");
                        } else {
                            System.out.println("Access Denied: Only Admins can register doctors.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter Patient Name: ");
                        String patientName = scanner.nextLine();
                        patientController.registerPatient(new Patient(1, patientName, "patient@hospital.com", "pass123", "None"));
                        System.out.println("Patient Registered Successfully.");
                        break;
                    case 3:
                        System.out.println("Booking a custom appointment...");
                        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                        String date = scanner.nextLine();
                        appointmentController.bookAppointment(new Appointment(1, 1, 1, date));
                        System.out.println("Custom Appointment Booked Successfully.");
                        break;
                    case 4:
                        if (loggedInUser instanceof Doctor) {
                            System.out.println("Creating a custom report...");
                            System.out.print("Enter Report Details: ");
                            String reportDetails = scanner.nextLine();
                            reportController.createReport(new Report(1, 1, 1, reportDetails));
                            System.out.println("Custom Report Created Successfully.");
                        } else {
                            System.out.println("Access Denied: Only Doctors can create reports.");
                        }
                        break;
                    case 5:
                        if (loggedInUser instanceof Pharmacist) {
                            System.out.println("Adding a custom medicine...");
                            System.out.print("Enter Medicine Name: ");
                            String medicineName = scanner.nextLine();
                            System.out.print("Enter Medicine Dosage: ");
                            String dosage = scanner.nextLine();
                            medicineController.addMedicine(new Medicine(1, medicineName, dosage));
                            System.out.println("Custom Medicine Added Successfully.");
                        } else {
                            System.out.println("Access Denied: Only Pharmacists can add medicines.");
                        }
                        break;
                    case 6:
                        if (loggedInUser instanceof Nurse) {
                            System.out.println("Allocating a custom bed...");
                            System.out.print("Enter Bed ID: ");
                            int bedId = scanner.nextInt();
                            System.out.print("Is Bed Occupied (true/false): ");
                            boolean isOccupied = scanner.nextBoolean();
                            bedController.addBed(new Bed(bedId, isOccupied));
                            System.out.println("Custom Bed Allocated Successfully.");
                        } else {
                            System.out.println("Access Denied: Only Nurses can allocate beds.");
                        }
                        break;
                    case 7:
                        if (loggedInUser instanceof Admin) {
                            System.out.println("Adding a custom hospital...");
                            System.out.print("Enter Hospital Name: ");
                            String hospitalName = scanner.nextLine();
                            System.out.print("Enter Hospital Location: ");
                            String location = scanner.nextLine();
                            hospitalController.addHospital(new Hospital(1, hospitalName, location));
                            System.out.println("Custom Hospital Added Successfully.");
                        } else {
                            System.out.println("Access Denied: Only Admins can add hospitals.");
                        }
                        break;
                    case 8:
                        System.out.println("Viewing bill and processing payment...");
                        System.out.println("Feature under development.");
                        break;
                    case 9:
                        System.out.println("Exiting the system...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        }
    }
}
