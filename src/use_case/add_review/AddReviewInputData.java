package use_case.add_review;

import entity.Restaurant;
import entity.User;

public class AddReviewInputData {
    private final String userID;
    private final Restaurant restaurant;

    public AddReviewInputData(String userID, Restaurant restaurant) {
        this.userID = userID;
        this.restaurant = restaurant;
    }

    public String getUserID() {return userID;}

    public Restaurant getRestaurant() {return restaurant;}
}
