package use_case.user_profile;

public class UserProfileOutputData {
    private final String username;
    private final String password;
    private final String location;
    private final Integer userID;

    public UserProfileOutputData(Integer userID, String username, String password, String location) {
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

    public Integer getUserID() {
        return userID;
    }
}
