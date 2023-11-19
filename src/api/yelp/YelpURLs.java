package api.yelp;

import api.Search.SearchCriteria;

public class YelpURLs {
    private static final String API_URL = "https://api.yelp.com/v3/businesses/";

    public String getURLByLocation(SearchCriteria criteria) {
        return String.format("%ssearch?location=%s&limit=%s&sort_by=%s",
                API_URL, criteria.getLocation(), criteria.getLimit(), criteria.getSortingMethod());
    }

    public String getURLByID(String id) {
        return String.format("%s%s", API_URL, id);
    }

    public static void getReviewURL() {}
}