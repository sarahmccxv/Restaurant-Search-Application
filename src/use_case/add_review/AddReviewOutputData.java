package use_case.add_review;

import entity.Restaurant;
import entity.User;

public class AddReviewOutputData {
    private User user;
    private Restaurant restaurant;

    public AddReviewOutputData(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

    public User getUser() {return user;}

    public Restaurant getRestaurant() {return restaurant;}

}
