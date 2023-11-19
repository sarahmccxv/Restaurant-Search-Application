package api.yelp;

import api.Search.SearchCriteria;

public class YelpURLs {
    private final String API_URL = "https://api.yelp.com/v3/businesses/";

    public String getURLWithCriteria(SearchCriteria criteria) {
        String url = String.format("%ssearch?location=%s&limit=%s&sort_by=%s&price%s",
                API_URL,
                criteria.getLocation(),
                criteria.getLimit(),
                criteria.getSortingMethod(),
                criteria.getPriceLevel());

        if (criteria.getName() != null)  {
            url = url + "&term=" + criteria.getName();
        }
        if (criteria.getCategory() != null) {
            url = String.format("%s&categories=%s", url, String.join(",", criteria.getCategory()));
        }

        return url;
    }

    public String getURLByID(String id) {
        return String.format("%s%s", API_URL, id);
    }

    public static void getReviewURL() {}
}