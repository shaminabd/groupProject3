package Controllers;

import Model.*;
import Services.AuthService;

public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public User login(String email, String password) {
        // Call the login method directly from authService
        User user = authService.login(email, password);
        if (user != null) {
            // Check the user type based on class name
            String role = user.getClass().getSimpleName();
            System.out.println("Welcome, " + role + "! You are logged in as " + role + ".");
            return user;
        } else {
            return null;
        }
    }

    public boolean signUp(User user, String role) {
        return authService.signUp(user, role);
    }
}
