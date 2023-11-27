package use_case.add_review;

import entity.Restaurant;
import entity.User;

public class AddReviewInputData {
    private final String userID;
    private final Restaurant restaurant;

    private final String previousView;

    public AddReviewInputData(String userID, Restaurant restaurant,
                              String previousView) {
        this.userID = userID;
        this.restaurant = restaurant;
        this.previousView = previousView;
    }

    public String getUserID() {return userID;}

    public Restaurant getRestaurant() {return restaurant;}

    public String getPreviousView() {return previousView;}
}
