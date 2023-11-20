package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface UserInterface {
    Integer getUserID();

    String getUsername();

    String getPassword();

    String getLocation();

    FavouritesList getFavouritesList();

    ArrayList<Review> getReviewsList();

    LocalDateTime getCreationTime();

    void setLocation(String location);

    void addToFavourites(Restaurant restaurant);

    void removeFavourite(String restaurantID);

    void addReview(Review review);

    void removeReview(Review review);

}