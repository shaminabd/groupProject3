package Repositories;

import Model.User;

public interface UserRepository {
    void addUser(User user);
    User getUserByEmail(String email);

    int getNextUserId();

    void addPatient(int newUserId, String healthHistory);

    void addDoctor(int newUserId, String specialization);
}