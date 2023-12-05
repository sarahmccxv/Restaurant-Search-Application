package data_access;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.register.RegisterUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements RegisterUserDataAccessInterface, LoginUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, User> usersID = new HashMap<>();

    /**
     * @param identifier the user's username
     * @return whether the user exists
     */
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public boolean existsByID(int identifier) {
        return users.containsValue(identifier);
    }

    @Override
    public boolean duplicatedID(int userID) {
        return usersID.containsKey(userID);
    }

    /**
     * @param user the data to save
     */
    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    private void clear() {
        users.clear();
    }

    @Override
    public User getByUsername(String username){
        return users.get(username);
    }

    @Override
    public User getByUserID(String userID) {
        return users.get(userID);
    }

    @Override
    public void update() {
        // TODO: To be implemented later
    }
}
