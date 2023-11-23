package interface_adapter.user_profile;

public class UserProfileState {
    private Integer userID = 0;
    private String username = "";
    private String password = "";
    private String location = "";

    public UserProfileState(UserProfileState copy) {
        userID = copy.userID;
        username = copy.username;
        password = copy.password;
        location = copy.location;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public UserProfileState() {}

    public Integer getUserID() {
        return userID;
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

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
