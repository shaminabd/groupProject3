package Menu;

import Controllers.AppointmentController;
import Controllers.ReportController;
import java.util.Scanner;

public class PatientMenu implements Menu {
    private final Scanner scanner;
    private final AppointmentController appointmentController;
    private final ReportController reportController;

    public PatientMenu(Scanner scanner, AppointmentController appointmentController, ReportController reportController) {
        this.scanner = scanner;
        this.appointmentController = appointmentController;
        this.reportController = reportController;
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
                System.out.print("Enter Doctor ID to make appointment: ");
                int doctorId = scanner.nextInt();
                scanner.nextLine();
                appointmentController.bookAppointment(doctorId);
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
