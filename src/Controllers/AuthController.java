package Controllers;

import Model.User;
import Services.AuthService;

public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public boolean login(String email, String password) {
        return authService.login(email, password);
    }

    public boolean signUp(User user, String role) {
        return authService.signUp(user, role);
    }
}