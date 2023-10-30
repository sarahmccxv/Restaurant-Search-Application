package entity;

import java.util.ArrayList;

public class Restaurant implements RestaurantInterface{
    private int restaurantID;
    private String restaurantName;
    private String address;
    private String phoneNumber;
    private ArrayList<Review> reviews;
    private ArrayList<String> categories;
    private ArrayList<Dish> menu;

    public Restaurant(int restaurantID, String restaurantName, String address, String phoneNumber,
                      ArrayList<String> categories, ArrayList<Dish> menu){
        this.restaurantID = restaurantID;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.reviews = new ArrayList<Review>();
        this.categories = categories;
        this.menu = menu;
    }

    public void addReviewandRating(Review review){
        reviews.add(review);
    };

    public int getRestaurantID() {
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

    public ArrayList<Dish> getMenu() {
        return menu;
    }
}
