package entity;

import java.util.ArrayList;

public interface UserInterface {
    int getUserID();

    String getUsername();

    String getPassword();

    String getLocation();

    FavouritesList getFavouritesList();

    ArrayList<Review> getReviewsList();

    void setLocation(String location);

    void addToFavourites(Restaurant restaurant);

    void removeFavourite(String restaurantID);

    void addReview(Review review);

    void removeReview(Review review);

}