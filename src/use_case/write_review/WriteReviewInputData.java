package use_case.write_review;

import entity.Restaurant;
import entity.User;

public class WriteReviewInputData {
    private final String userID;
    private final Restaurant restaurant;

    public WriteReviewInputData(String userID, Restaurant restaurant) {
        this.userID = userID;
        this.restaurant = restaurant;
    }

    public String getUserID() { return userID; }

    public Restaurant getRestaurant() { return restaurant;}
}
