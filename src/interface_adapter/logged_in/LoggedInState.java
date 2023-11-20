package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "";
    private Integer userID = 0;
    private String password = "";

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        userID = copy.userID;
        password = copy.password;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public Integer getUserID() {return userID;}
    public String getPassword() {
        return password;
    }
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    public void setUserID(Integer newUserID) {
        this.userID = newUserID;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
