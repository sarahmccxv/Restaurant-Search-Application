package use_case.write_review;

import entity.Restaurant;
import entity.User;

public class WriteReviewOutputData {
    private User user;
    private Restaurant restaurant;
    private String successMessage;
    private String failureMessage;

    public WriteReviewOutputData(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
        this.successMessage = "Review for " + restaurant.getRestaurantName() +
                "successfully saved.";
        this.failureMessage = "Please specify rating from 0-5 with 1 decimal place, and the last digit should be 0 or 5. Review content must be not empty.";
    }

    public User getUser() {return user;}

    public Restaurant getRestaurant(){return restaurant;}

    public String getSuccessMessage(){return successMessage;}

    public String getFailureMessage(){return failureMessage;}
}
