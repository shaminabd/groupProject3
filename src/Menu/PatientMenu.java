package Menu;

import Controllers.AppointmentController;
import Controllers.DoctorController;
import Controllers.ReportController;
import Model.Doctor;
import Model.Patient;
import Repositories.Impl.PatientRepositoryImpl;
import Repositories.Impl.DoctorRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PatientMenu implements Menu {
    private final Scanner scanner;
    private final AppointmentController appointmentController;
    private final ReportController reportController;
    private final Patient loggedInPatient;
    private final PatientRepositoryImpl patientRepository;
    private final DoctorRepositoryImpl doctorRepository;

    public PatientMenu(Scanner scanner, AppointmentController appointmentController, ReportController reportController
    , Patient loggedInPatient, PatientRepositoryImpl patientRepository, DoctorRepositoryImpl doctorRepository) {
        this.scanner = scanner;
        this.appointmentController = appointmentController;
        this.reportController = reportController;
        this.loggedInPatient = loggedInPatient;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nHospital Management System Menu (Patient):");
        System.out.println("1. Make Appointment");
        System.out.println("2. View Report");
        System.out.println("3. Log Out");
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:

                System.out.println("Choose a patient for the appointment:");

                // Fetch and display the list of patients from the database
                List<Doctor> doctors = doctorRepository.getAllDoctors();
                for (int i = 0; i < doctors.size(); i++) {
                    System.out.println((i + 1) + ". " + doctors.get(i).getName());
                }
                System.out.print("Enter the patient number: ");
                int doctorChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                Doctor selectedDoctor = doctors.get(doctorChoice - 1);

                System.out.print("Enter the appointment date (yyyy-mm-dd): ");
                String appointmentDateStr = scanner.nextLine();
                LocalDate appointmentDate = LocalDate.parse(appointmentDateStr);


                appointmentController.bookAppointment(selectedDoctor.getId(),loggedInPatient.getId(), appointmentDate);
                break;
            case 2:
                reportController.viewReportsForPatient();
                break;
            case 3:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }
}
