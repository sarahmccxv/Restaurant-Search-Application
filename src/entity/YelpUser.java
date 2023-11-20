package entity;

public class YelpUser extends CommonUser {
    private final String userID;

    public YelpUser(String userID, String username) {
        super(username);
        this.userID = userID;
    }

    public String getUsername() {
        return super.getUsername();
    }

    public String getUserID() {
        return userID;
    }
}
