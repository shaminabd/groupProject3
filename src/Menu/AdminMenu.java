package Menu;

import Controllers.*;
import Model.Hospital;
import Model.Report;

import java.util.Scanner;

public class AdminMenu implements Menu {
    private final Scanner scanner;
    private final DoctorController doctorController;
    private final PatientController patientController;
    private final AppointmentController appointmentController;
    private final MedicineController medicineController;
    private final ReportController reportController;
    private final HospitalController hospitalController;

    public AdminMenu(Scanner scanner, DoctorController doctorController, PatientController patientController,
                     AppointmentController appointmentController, MedicineController medicineController,
                     ReportController reportController, HospitalController hospitalController) {
        this.scanner = scanner;
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.appointmentController = appointmentController;
        this.medicineController = medicineController;
        this.reportController = reportController;
        this.hospitalController = hospitalController;
    }

    @Override
    public void displayMenu() {
        System.out.println("\nHospital Management System Menu (Admin):");
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
        System.out.println("11. Delete Hospital");
        System.out.println("12. Add Doctor to Hospital");
        System.out.println("13. Remove Doctor from Hospital");
        System.out.println("14. Delete Report");
        System.out.println("15. Log Out");
    }

    @Override
    public void handleChoice(int choice) {
        switch (choice) {
            case 1:
                doctorController.viewDoctors();
                break;
            case 2:
                System.out.print("Enter Doctor ID to remove: ");
                int doctorId = scanner.nextInt();
                doctorController.removeDoctor(doctorId);
                break;
            case 3:
                patientController.viewPatients();
                break;
            case 4:
                System.out.print("Enter Patient ID to remove: ");
                int patientId = scanner.nextInt();
                patientController.removePatient(patientId);
                break;
            case 5:
                appointmentController.viewAppointments();
                break;
            case 6:
                System.out.print("Enter Appointment ID to remove: ");
                int appointmentId = scanner.nextInt();
                appointmentController.removeAppointment(appointmentId);
                break;
            case 7:
                medicineController.viewMedicines();
                break;
            case 8:
                System.out.print("Enter Medicine ID to remove: ");
                int medicineId = scanner.nextInt();
                medicineController.removeMedicine(medicineId);
                break;
            case 9:
                System.out.println("Generating a custom report (receipt)... ");
                System.out.print("Enter Report Details: ");
                String reportDetails = scanner.nextLine();
                reportController.createReport(new Report(1, 1, 1, reportDetails));
                break;
            case 10:
                System.out.print("Enter Hospital Name: ");
                String hospitalName = scanner.nextLine();
                System.out.print("Enter Hospital Location: ");
                String location = scanner.nextLine();
                hospitalController.addHospital(new Hospital(1, hospitalName, location));
                break;
            case 11:
                System.out.print("Enter Hospital ID to delete: ");
                int hospitalId = scanner.nextInt();
                hospitalController.removeHospital(hospitalId);
                break;
            case 12:
                System.out.print("Enter Hospital ID: ");
                int addHospitalId = scanner.nextInt();
                System.out.print("Enter Doctor ID to add: ");
                int addDoctorId = scanner.nextInt();
                hospitalController.addDoctorToHospital(addHospitalId, addDoctorId);
                break;
            case 13:
                System.out.print("Enter Hospital ID: ");
                int removeHospitalId = scanner.nextInt();
                System.out.print("Enter Doctor ID to remove: ");
                int removeDoctorId = scanner.nextInt();
                hospitalController.removeDoctorFromHospital(removeHospitalId, removeDoctorId);
                break;
            case 14:
                System.out.print("Enter Report ID to delete: ");
                int reportId = scanner.nextInt();
                reportController.removeReport(reportId);
                break;
            case 15:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }
}
