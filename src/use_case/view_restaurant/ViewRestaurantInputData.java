package use_case.view_restaurant;

public class ViewRestaurantInputData {
    private final Integer userID;
    private final String username;
    private final String password;

    public ViewRestaurantInputData(Integer userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
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
}
