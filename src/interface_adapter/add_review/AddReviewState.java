package interface_adapter.add_review;

import entity.Restaurant;
import entity.User;

public class AddReviewState {
    User user;
    Restaurant restaurant;

    String previous_view;

    public AddReviewState(){}

    public void setUser(User user){this.user = user;}

    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    public void setPrevious_view(String previous_view) {this.previous_view = previous_view;}

    public User getUser() {return user;}

    public Restaurant getRestaurant(){return restaurant;}

    public String getPrevious_view(){return previous_view;}

}
