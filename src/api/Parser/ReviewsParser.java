package api.Parser;

import entity.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReviewsParser {
    public static ArrayList<YelpReview> parseFrom(JSONObject jsonObject, String restaurantID) {
         try {
            ArrayList<YelpReview> reviewArrayList = new ArrayList<>();
            YelpReviewFactory yelpReviewFactory = new YelpReviewFactory();
            JSONArray reviewsArray = jsonObject.getJSONArray("reviews");

            for (int i = 0; i < reviewsArray.length(); i++) {
                String authorID = reviewsArray.getJSONObject(i).getJSONObject("user").getString("id");
                String authorName= reviewsArray.getJSONObject(i).getJSONObject("user").getString("name");
                YelpUserFactory yelpUserFactory = new YelpUserFactory();
                YelpUser yelpUser = yelpUserFactory.create(authorID, authorName);

                String reviewID = reviewsArray.getJSONObject(i).getString("id");
                Float rating = reviewsArray.getJSONObject(i).getFloat("rating");
                String content = reviewsArray.getJSONObject(i).getString("text");

                String dateTimeString = reviewsArray.getJSONObject(i).getString("time_created");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime creationTime = LocalDateTime.parse(dateTimeString, formatter);
                reviewArrayList.add(yelpReviewFactory.create(reviewID, yelpUser, restaurantID, rating, content, creationTime));
            }
            return reviewArrayList;
         } catch (JSONException e) {
             throw new JSONException(e);
         }
     }
}