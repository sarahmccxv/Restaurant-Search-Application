package use_case.restaurant;

public class RestaurantInputData {
    private final String userID;
    private final String username;
    private final String password;
    private final String restaurantID;
    private String previousView;

    public RestaurantInputData(String userID, String username, String password, String restaurantID,
                               String previousView) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.restaurantID = restaurantID;
        this.previousView = previousView;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getPreviousView() {return previousView;}
}
