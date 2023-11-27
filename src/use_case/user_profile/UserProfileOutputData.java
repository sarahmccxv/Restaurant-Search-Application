package use_case.user_profile;

public class UserProfileOutputData {
    private final String username;
    private final String password;
    private final String location;
    private final String userID;

    public UserProfileOutputData(String userID, String username, String password, String location) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public String getUserID() {
        return userID;
    }
}
