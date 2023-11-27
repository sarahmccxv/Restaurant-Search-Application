package use_case.write_review;

import entity.Restaurant;
import entity.User;

public class WriteReviewInputData {
    private final User user;
    private final Restaurant restaurant;

    public WriteReviewInputData(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }

    public User getUserID() { return user; }

    public Restaurant getRestaurant() { return restaurant;}
}
