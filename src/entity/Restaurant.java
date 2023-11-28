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
        this.restaurantName = restaurantName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.reviews = new ArrayList<Review>();
        this.categories = categories;
    }

    public void addReviewandRating(Review review){
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
    public int getReviewSize(){return reviews.size();}

    public ArrayList<String> getCategories() {
        return categories;
    }

    @Override
    public String toString(){
        return restaurantName +
                "\nPhone Number: " + phoneNumber +
                "\nAddress: " + address;
    }
}
