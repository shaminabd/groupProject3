package Services;

import Model.Admin;
import Model.Doctor;
import Model.Nurse;
import Model.User;
import Repositories.UserRepository;

public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        return user != null && user.getPassword().equals(password);
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
        } else {
            // Default role
            user = new User(user.getId(), user.getName(), user.getEmail(), user.getPassword());
        }

        userRepository.addUser(user);
        return true; // User registered successfully
    }
}
