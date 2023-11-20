package api.yelp;

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

        if (criteria.getCategory() != null) {
            for (String category : criteria.getCategory()) {
                url.append("&categories=").append(category);
            }
        }

        return url.toString();
    }

    public String getURLByID(String id) {
        return String.format("%s%s", API_URL, id);
    }

    public static void getReviewURL() {}
}