package entity;

import java.util.ArrayList;

interface RestaurantInterface {

    void addReviewandRating(Review review);

    int getRestaurantID();

    String getRestaurantName();

    String getAddress();

    String getPhoneNumber();

    ArrayList<Review> getReviews();

    ArrayList<String> getCategories();

    ArrayList<Dish> getMenu();

}
