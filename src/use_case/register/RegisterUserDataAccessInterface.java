package use_case.register;

import entity.User;

public interface RegisterUserDataAccessInterface {
    boolean existsByID(int identifier);

    void save(User user);
}
