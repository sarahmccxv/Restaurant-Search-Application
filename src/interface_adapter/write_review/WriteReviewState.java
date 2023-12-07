package interface_adapter.write_review;

import entity.Restaurant;
import entity.User;

import java.util.Random;

public class WriteReviewState {
    private User user;
    private Restaurant restaurant;
    private String rating = "";
    private String ratingError = null;
    private String content = "";
    private String previous_view = "";
    private String message = "";
    private Integer status = 0;

    public WriteReviewState(WriteReviewState copy) {
        user = copy.user;
        restaurant = copy.restaurant;
        rating = copy.rating;
        ratingError = copy.ratingError;
        content = copy.content;
        previous_view = copy.previous_view;
        status = copy.status;
    }

    public WriteReviewState(){}

    public void setUser(User user) {this.user = user;}

    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    public void setRating(String rating) {this.rating = rating;}

    public void setRatingError(String error) {this.ratingError = error;}

    public void setContent(String content) {this.content = content;}

    public void setMessage(String message) {this.message = message;}

    public void setPrevious_view(String previous_view) {this.previous_view = previous_view;}

    public void setStatus(Integer status) {this.status = status;}

    public User getUser(){return user;}

    public Restaurant getRestaurant(){return restaurant;}

    public String getRating(){return rating;}

    public String getRatingError(){return ratingError;}

    public String getContent(){return content;}

    public String getMessage(){return message;}

    public String getPrevious_view() {return previous_view;}

    public Integer getStatus() {return status;}
}
