package Services;

import Model.User;

import Repositories.UserRepository;
public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user != null) {
            return user.getPassword().equals(password);  // Ensure the comparison happens here
        }
        return false;
    }

    public void signUp(User user) {
        userRepository.addUser(user);
    }
}
