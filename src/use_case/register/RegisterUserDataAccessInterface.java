package use_case.register;

import entity.User;

public interface RegisterUserDataAccessInterface {
    boolean existsByID(int identifier);

    boolean duplicatedID(int userID);

    void save(User user);

    User get(String username);
}
