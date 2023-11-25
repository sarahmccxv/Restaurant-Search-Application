package api.yelp;

import api.Review.ReviewCriteria;
import api.Search.SearchCriteria;

public class YelpURIs {
    private final String API_URI = "https://api.yelp.com/v3/businesses/";

    public String getRestaurantURIWithCriteria(SearchCriteria criteria) {
        String res = String.format("%ssearch?location=%s&limit=%s&sort_by=%s&price=%s",
                API_URI,
                criteria.getLocation(),
                criteria.getLimit(),
                criteria.getSortingMethod(),
                criteria.getPriceLevel());

        if (criteria.getCategory() != null) {
            res += "&categories=" + criteria.getCategory();
        }

        if (criteria.getName() != null)  {
            res += "&term=" + criteria.getName();
        }

        return res;
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