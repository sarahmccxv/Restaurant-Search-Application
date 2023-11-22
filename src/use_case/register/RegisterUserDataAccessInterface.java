package use_case.register;

import entity.User;

public interface RegisterUserDataAccessInterface {
    boolean existsByID(int identifier);

    boolean existsByName(String username);

    boolean duplicatedID(int userID);

    void save(User user);

    User getByUsername(String username);

    User getByUserID(String userID);

    void update();
}
