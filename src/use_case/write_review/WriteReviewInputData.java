package use_case.write_review;

import entity.Restaurant;
import entity.User;

public class WriteReviewInputData {
    private final User user;
    private final Restaurant restaurant;
    private final String rating;
    private final String content;


    public WriteReviewInputData(User user, Restaurant restaurant,
                                String rating, String content) {
        this.user = user;
        this.restaurant = restaurant;
        this.rating = rating;
        this.content = content;
    }

    public User getUser() { return user; }

    public Restaurant getRestaurant() { return restaurant;}

    public String getRating() {return rating;}

    public String getContent() {return content;}
}
