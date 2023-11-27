package api.response;

import entity.YelpReview;

import java.util.ArrayList;

public class ReviewsResponse extends Response {
    public ReviewsResponse(String response) {
        super(response);
    }

    public ArrayList<YelpReview> getReviews(String restaurantID) {
        return parser.parseReviews(response, restaurantID);
    }
}