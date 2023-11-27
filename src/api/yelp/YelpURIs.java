package api.yelp;

import api.Review.ReviewCriteria;
import api.Search.SearchCriteria;


public class YelpURIs {
    private final String API_URI = "https://api.yelp.com/v3/businesses/";

    public String getRestaurantURIWithCriteria(SearchCriteria criteria) {
        StringBuilder builder = new StringBuilder(API_URI + "search?location=")
                .append(criteria.getLocation())
                .append("&limit=").append(criteria.getLimit())
                .append("&sort_by=").append(criteria.getSortingMethod())
                .append("&price=").append(criteria.getPriceLevel());

        if (criteria.getName() != null) {
            String restaurantName = criteria.getName().replace(" ", "%20");
            builder.append("&term=").append(restaurantName);
        } else if (criteria.getCategory() != null) {
            builder.append("&categories=").append(criteria.getCategory());
        }

        return builder.toString();
    }

    public String getRestaurantURIByID(String id) {
        return String.format("%s%s", API_URI, id);
    }

    public String getReviewsURI(ReviewCriteria criteria) {
        return String.format("%s%s/reviews?limit=%s&sort_by=%s",
                API_URI,
                criteria.getRestaurantID(),
                criteria.getLimit(),
                criteria.getSortingMethod());
    }
}