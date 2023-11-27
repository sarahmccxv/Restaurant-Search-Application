package use_case.user_profile;

public class UserProfileInputData {
    final private Integer userID;
    final private String username;
    final private String password;
    final private String location;

    public UserProfileInputData(Integer userID, String username, String password, String location) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.location = location;
    }

    Integer getUserID() {
        return userID;
    }
    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    String getLocation() {
        return location;
    }
}
