package use_case.restaurant;

public class RestaurantInputData {
    private final Integer userID;
    private final String username;
    private final String password;
    private final String restaurantID;
    private String previousView;

    public RestaurantInputData(Integer userID, String username, String password, String restaurantID,
                               String previousView) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.restaurantID = restaurantID;
        this.previousView = previousView;
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

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getPreviousView() {return previousView;}
}
