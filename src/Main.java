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

// Check if this method is being called.

        DatabaseInitializer.initializeDatabase();
        System.out.println("Hospital Management System Starting...");

        // Initialize repository implementations
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl();
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl();
        ReportRepositoryImpl reportRepository = new ReportRepositoryImpl();
        MedicineRepositoryImpl medicineRepository = new MedicineRepositoryImpl();
        BedRepositoryImpl bedRepository = new BedRepositoryImpl();
        HospitalRepositoryImpl hospitalRepository = new HospitalRepositoryImpl();

        // Initialize services
        AuthService authService = new AuthService(userRepository);
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        ReportService reportService = new ReportService(reportRepository);
        MedicineService medicineService = new MedicineService(medicineRepository);
        BedService bedService = new BedService(bedRepository);
        HospitalService hospitalService = new HospitalService(hospitalRepository);

        // Initialize controllers
        AuthController authController = new AuthController(authService);
        DoctorController doctorController = new DoctorController(doctorService);
        PatientController patientController = new PatientController(patientService);
        AppointmentController appointmentController = new AppointmentController(appointmentService);
        ReportController reportController = new ReportController(reportService);
        MedicineController medicineController = new MedicineController(medicineService);
        BedController bedController = new BedController(bedService);
        HospitalController hospitalController = new HospitalController(hospitalService);

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
            System.out.println("1. Register Doctor (Admin Only)");
            System.out.println("2. Register Patient");
            System.out.println("3. Book Appointment");
            System.out.println("4. Create Report (Doctors Only)");
            System.out.println("5. Add Medicine (Pharmacists Only)");
            System.out.println("6. Allocate Bed (Nurses Only)");
            System.out.println("7. Add Hospital (Admin Only)");
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
                        doctorController.registerDoctor(new Doctor(1, doctorName, "email@hospital.com", "pass123", "General"));
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
                    System.out.println("Booking an appointment...");
                    appointmentController.bookAppointment(new Appointment(1, 1, 1, "2025-02-01"));
                    System.out.println("Appointment Booked Successfully.");
                    break;
                case 4:
                    if (loggedInUser instanceof Doctor) {
                        System.out.println("Creating a report...");
                        reportController.createReport(new Report(1, 1, 1, "Report Details"));
                        System.out.println("Report Created Successfully.");
                    } else {
                        System.out.println("Access Denied: Only Doctors can create reports.");
                    }
                    break;
                case 5:
                    if (loggedInUser instanceof Pharmacist) {
                        System.out.println("Adding a medicine...");
                        medicineController.addMedicine(new Medicine(1, "Paracetamol", "500mg"));
                        System.out.println("Medicine Added Successfully.");
                    } else {
                        System.out.println("Access Denied: Only Pharmacists can add medicines.");
                    }
                    break;
                case 6:
                    if (loggedInUser instanceof Nurse) {
                        System.out.println("Allocating a bed...");
                        bedController.addBed(new Bed(1, false));
                        System.out.println("Bed Allocated Successfully.");
                    } else {
                        System.out.println("Access Denied: Only Nurses can allocate beds.");
                    }
                    break;
                case 7:
                    if (loggedInUser instanceof Admin) {
                        System.out.println("Adding a hospital...");
                        hospitalController.addHospital(new Hospital(1, "General Hospital", "Downtown"));
                        System.out.println("Hospital Added Successfully.");
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
