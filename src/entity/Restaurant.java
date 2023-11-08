package entity;

import java.util.ArrayList;

public class Restaurant implements RestaurantInterface{
    private String restaurantID;
    private String restaurantName;
    private String address;
    private String phoneNumber;
    private ArrayList<Review> reviews;
    private ArrayList<String> categories;

    public Restaurant(String restaurantID, String restaurantName, String address, String phoneNumber,
                      ArrayList<String> categories){
        this.restaurantID = restaurantID;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.categories = categories;
    }

    public void addReview(Review review){
        reviews.add(review);
    };

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

}
