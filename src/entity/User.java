package entity;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class User extends CommonUser implements UserInterface {
    private final Integer userID;
    private final String password;
    private final LocalDateTime creationTime;
    private String location;
    private FavouritesList favouritesList = new FavouritesList();
    private final ArrayList<Review> reviewsList = new ArrayList<>();

    public User(Integer userID, String username, String password, String location,
                LocalDateTime creationTime){
        super(username);
        this.userID = userID;
        this.password = password;
        this.location = location;
        this.creationTime = creationTime;
        this.favouritesList = new FavouritesList();
    }

    public Integer getUserID() {
        return userID;
    }

    public String getUsername() {
        return super.getUsername();
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

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
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