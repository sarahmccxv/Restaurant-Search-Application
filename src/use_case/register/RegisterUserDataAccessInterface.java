package use_case.register;

import entity.User;

public interface RegisterUserDataAccessInterface {
    boolean existsByName(String identifier);

    boolean duplicatedID(int userID);

    void save(User user);
}
