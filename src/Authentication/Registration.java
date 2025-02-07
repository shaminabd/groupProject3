package Authentication;
import java.util.Scanner;
import Controllers.AuthController;
import Model.*;
import Repositories.Impl.DoctorRepositoryImpl;
import Repositories.Impl.PatientRepositoryImpl;
import Repositories.Impl.UserRepositoryImpl;


public class Registration {
    private final AuthController authController;
    private final UserRepositoryImpl userRepository;
    private final DoctorRepositoryImpl doctorRepository;
    private final PatientRepositoryImpl patientRepository;
    private final Scanner scanner;

    public Registration(AuthController authController, UserRepositoryImpl userRepository,
                        DoctorRepositoryImpl doctorRepository, PatientRepositoryImpl patientRepository,
                        Scanner scanner) {
        this.authController = authController;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.scanner = scanner;
    }

    public void register() {
        System.out.println("Enter your role (Admin, Doctor, Patient): ");  // Removed Nurse, Pharmacist
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
            // Add user to the doctors table
            doctorRepository.addDoctor((Doctor) user);  // assuming addDoctor() adds to doctors table
        } else if (role.equalsIgnoreCase("Patient")) {
            System.out.print("Enter Patient Health History: ");
            String healthHistory = scanner.nextLine();
            user = new Patient(1, name, email, password, healthHistory);
            // Add user to the patients table
            patientRepository.addPatient((Patient) user);  // assuming addPatient() adds to patients table
        } else {
            System.out.println("Invalid role. Registration failed.");
            return;
        }

        if (userRepository.getUserByEmail(email) != null) {
            System.out.println(role + " already exists.");
        } else {
            boolean isRegistered = authController.signUp(user, role);
            if (isRegistered) {
                System.out.println(role + " registered successfully.");
            } else {
                System.out.println("An error occurred during registration.");
            }
        }
    }
}
