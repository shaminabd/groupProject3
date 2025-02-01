package Services;

import Model.*;
import Repositories.UserRepository;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User login(String email, String password) {

        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            System.out.println("No user found with email: " + email);
            return null;
        }


        if (user.getPassword().equals(password)) {
            System.out.println("Password match successful for: " + user.getEmail());
            return user;
        } else {
            System.out.println("Password mismatch.");
            return null;
        }
    }

    public boolean signUp(User user, String role) {
        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            return false;
        }

        int newUserId = userRepository.getNextUserId();

        switch (role.toLowerCase()) {
            case "admin":
                user = new Admin(newUserId, user.getName(), user.getEmail(), user.getPassword(), "admin");
                break;
            case "doctor":
                if (user instanceof Doctor) {
                    Doctor doctor = (Doctor) user;
                    user = new Doctor(newUserId, doctor.getName(), doctor.getEmail(), doctor.getPassword(), doctor.getSpecialization());
                }
                break;
            case "nurse":
                user = new Nurse(newUserId, user.getName(), user.getEmail(), user.getPassword());
                break;
            case "pharmacist":
                user = new Pharmacist(newUserId, user.getName(), user.getEmail(), user.getPassword());
                break;
            case "patient":
                if (user instanceof Patient) {
                    Patient patient = (Patient) user;
                    user = new Patient(newUserId, patient.getName(), patient.getEmail(), patient.getPassword(), patient.getHealthHistory());
                }
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                return false;
        }

        userRepository.addUser(user);
        return true;
    }


}
