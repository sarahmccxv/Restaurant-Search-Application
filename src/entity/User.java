package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class User implements UserInterface {
    private final int userID;
    private final String username;
    private final String password;
    private final LocalDateTime creationTime;
    private String location;
    private final FavouritesList favouritesList = new FavouritesList();
    private final ArrayList<Review> reviewsList = new ArrayList<>();

    public User(int userID, String username, String password, String location,
                LocalDateTime creationTime){
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.location = location;
        this.creationTime = creationTime;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public FavouritesList getFavouritesList() {
        return favouritesList;
    }

    public ArrayList<Review> getReviewsList() {
        return reviewsList;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addToFavourites(Restaurant restaurant){
        favouritesList.add(restaurant);
    }

    public void removeFavourite(String restaurantID){
        favouritesList.remove(restaurantID);
    }

    public void addReview(Review review){
        reviewsList.add(review);
    }

    public void removeReview(Review review){
        reviewsList.remove(review);
    }

}