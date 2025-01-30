package Repositories;

import Model.User;

public interface UserRepository {
    void addUser(User user);
    User getUserByEmail(String email);
}