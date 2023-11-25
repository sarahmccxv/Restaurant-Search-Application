package use_case.view_restaurant;

public class ViewRestaurantInputData {
    private final Integer userID;
    private final String username;
    private final String password;
    private final String location;

    public ViewRestaurantInputData(Integer userID, String username, String password, String location) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.location = location;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getLocation() { return location; }
}
