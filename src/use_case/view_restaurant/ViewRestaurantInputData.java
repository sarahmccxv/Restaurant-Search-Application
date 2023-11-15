package use_case.view_restaurant;

public class ViewRestaurantInputData {
    private final int userID;

    public ViewRestaurantInputData(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }
}
