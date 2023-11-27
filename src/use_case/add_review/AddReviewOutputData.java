package use_case.add_review;

import entity.Restaurant;
import entity.User;

public class AddReviewOutputData {
    private User user;
    private Restaurant restaurant;
    private String previousView;

    public AddReviewOutputData(User user, Restaurant restaurant, String previousView) {
        this.user = user;
        this.restaurant = restaurant;
        this.previousView = previousView;
    }

    public User getUser() {return user;}

    public Restaurant getRestaurant() {return restaurant;}

    public String getPreviousView() {return previousView;}

}
