// DoctorMenu.java
package Menu;

import Controllers.*;
import Model.Doctor;
import Model.Report;

import java.util.Scanner;

public class DoctorMenu implements Menu {
    private final Scanner scanner;
    private final AppointmentController appointmentController;
    private final ReportController reportController;
    private final MedicineController medicineController;
    private final BedController bedController;
    private final Doctor loggedInDoctor;

    public DoctorMenu(Scanner scanner, AppointmentController appointmentController, ReportController reportController,
                      MedicineController medicineController, BedController bedController, Doctor loggedInDoctor) {
        this.scanner = scanner;
        this.appointmentController = appointmentController;
        this.reportController = reportController;
        this.medicineController = medicineController;
        this.bedController = bedController;
        this.loggedInDoctor = loggedInDoctor;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nHospital Management System Menu (Doctor):");
        System.out.println("1. View Medicines");
        System.out.println("2. Give Medicine to Patient");
        System.out.println("3. Create Report");
        System.out.println("4. View Report");
        System.out.println("5. Check Bed Availability");
        System.out.println("6. Create Bed");
        System.out.println("7. Remove Bed");
        System.out.println("8. Log Out");
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                medicineController.viewMedicines();
                break;
            case 2:
                if (loggedInDoctor.getSpecialization().equals("Psychologist")) {
                    System.out.println("Psychologists cannot prescribe medicines.");
                } else {
                    System.out.print("Enter Patient ID to give medicine: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Medicine ID: ");
                    int medicineId = scanner.nextInt();
                    scanner.nextLine();
                    medicineController.giveMedicineToPatient(patientId, medicineId);
                }
                break;
            case 3:
                System.out.print("Enter Report Details: ");
                String reportDetails = scanner.nextLine();
                reportController.createReport(new Report(1, 1, 1, reportDetails));
                break;
            case 4:
                reportController.viewReportsForDoctor(loggedInDoctor);
                break;
            case 5:
                bedController.checkAvailability();
                break;
            case 6:
                bedController.createBed();
                break;
            case 7:
                bedController.removeBed();
                break;
            case 8:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }
}
