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
        // Logic to create user based on the role
        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            return false; // User already exists
        }

        if (role.equals("Admin")) {
            user = new Admin(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        } else if (role.equals("Doctor")) {
            user = new Doctor(user.getId(), user.getName(), user.getEmail(), user.getPassword(), "General");
        } else if (role.equals("Nurse")) {
            user = new Nurse(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        } else if (role.equals("Pharmacist")) {
            user = new Pharmacist(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        } else {
            // Default role
            user = new Patient(user.getId(), user.getName(), user.getEmail(), user.getPassword(), "N/A");
        }

        userRepository.addUser(user);
        return true; // User registered successfully
    }
}
