package Controllers;

import Model.*;
import Services.AuthService;

public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public User login(String email, String password) {
        User user = authService.login(email, password);

        if (user != null) {
            System.out.println("Login successful as " + user.getRole());
            return user;
        }
        System.out.println("Invalid credentials.");
        return null;
    }



    public boolean signUp(User user, String role) {
        return authService.signUp(user, role);
    }
}
