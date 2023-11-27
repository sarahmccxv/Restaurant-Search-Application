package use_case.login;

import entity.User;


public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User getByUsername(String username);

    User getByUserID(String userID);
}
