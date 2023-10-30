package entity;

import java.util.ArrayList;

public interface UserInterface {
    int getUserID();

    String getUsername();

    String getPassword();

    String getLocation();

    ArrayList<Restaurant> getFavouritesList();

    ArrayList<Review> getReviewsList();

    void setLocation(String location);

    void addToFavourites(Restaurant restaurant);

    void removeToFavourites(Restaurant restaurant);

    void addReview(Review review);

    void removeReview(Review review);

}