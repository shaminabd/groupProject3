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

    public void signUp(User user) {
        authService.signUp(user);
    }
}