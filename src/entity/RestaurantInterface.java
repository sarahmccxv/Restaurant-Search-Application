package entity;

import java.util.ArrayList;

interface RestaurantInterface {

    void addReviewandRating(Review review);

    String getRestaurantID();

    String getRestaurantName();

    String getAddress();

    String getPhoneNumber();

    ArrayList<Review> getReviews();

    ArrayList<String> getCategories();

}
