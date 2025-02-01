package Services;

import Model.*;
import Repositories.UserRepository;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Authenticate user and return the correct type
    public User login(String email, String password) {
        // Authenticate user directly in the login method
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            System.out.println("No user found with email: " + email);
            return null;
        }

        // Check if the entered password matches the stored password
        if (user.getPassword().equals(password)) {
            System.out.println("Password match successful for: " + user.getEmail());  // Debugging line
            return user;
        } else {
            System.out.println("Password mismatch.");
            return null; // Return null if credentials are incorrect
        }
    }

    public boolean signUp(User user, String role) {
        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            return false; // User already exists
        }

        int newUserId = userRepository.getNextUserId(); // Implement this method in repository

        switch (role.toLowerCase()) {
            case "admin":
                user = new Admin(newUserId, user.getName(), user.getEmail(), user.getPassword(), "admin");
                break;
            case "doctor":
                if (user instanceof Doctor) {
                    Doctor doctor = (Doctor) user;
                    user = new Doctor(newUserId, doctor.getName(), doctor.getEmail(), doctor.getPassword(), "doctor", doctor.getSpecialization());
                }
                break;
            case "nurse":
                user = new Nurse(newUserId, user.getName(), user.getEmail(), user.getPassword(), "nurse");
                break;
            case "pharmacist":
                user = new Pharmacist(newUserId, user.getName(), user.getEmail(), user.getPassword(), "pharmacist");
                break;
            case "patient":
                if (user instanceof Patient) {
                    Patient patient = (Patient) user;
                    user = new Patient(newUserId, patient.getName(), patient.getEmail(), patient.getPassword(), "patient", patient.getHealthHistory());
                }
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                return false;
        }

        userRepository.addUser(user);
        return true; // User registered successfully
    }


}
