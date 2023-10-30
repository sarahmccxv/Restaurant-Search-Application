package use_case.register;

import entity.User;

public interface RegisterUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
