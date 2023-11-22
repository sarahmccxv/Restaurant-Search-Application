package api.yelp;

import api.Review.ReviewCriteria;
import api.Search.SearchCriteria;

public class YelpURLs {
    private final String API_URL = "https://api.yelp.com/v3/businesses/";

    public String getURLWithCriteria(SearchCriteria criteria) {
        StringBuilder url = new StringBuilder(String.format("%ssearch?location=%s&limit=%s&sort_by=%s&price%s",
                API_URL,
                criteria.getLocation(),
                criteria.getLimit(),
                criteria.getSortingMethod(),
                criteria.getPriceLevel()));

        if (criteria.getName() != null)  {
            url.append("&term=").append(criteria.getName());
        }

        if(criteria.getCategory() != null) {
            url.append("&categories=").append(criteria.getCategory());
        }

        return url.toString();
    }

    public String getURLByID(String id) {
        return String.format("%s%s", API_URL, id);
    }

    public String getReviewsURL(ReviewCriteria criteria) {
        return String.format("%s/reviews?limit=%s&sort_by=%s",
                API_URL,
                criteria.getLimit(),
                criteria.getSortingMethod());
    }
}