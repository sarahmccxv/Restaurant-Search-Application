package entity;

public class UserFactory {
    public User create(int userID, String username, String password, String location) {
        return new User(userID, username, password, location);
    }
}
