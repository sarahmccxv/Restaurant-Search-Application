package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";
    private String userID = "";
    private String password = "";
    private String location = "";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        userID = copy.userID;
        password = copy.password;
        location = copy.location;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public String getUserID() {return userID;}
    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    public void setUserID(String newUserID) {
        this.userID = newUserID;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setLocation(String newLocation) {
        this.location = newLocation;
    }
}
