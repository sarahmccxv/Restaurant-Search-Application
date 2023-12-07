package api_testing.YelpTesting;

import api.Review.ReviewCriteria;
import api.Review.ReviewSortingMethods;
import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import api.yelp.YelpURIs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YelpURIsTest {

    @Test
    public void testGetRestaurantURIWithCriteria() {
        // Create a SearchCriteria object for testing
        SearchCriteria criteria = new SearchCriteria.Builder().build();
        criteria.setLocation("New York");
        criteria.setLimit(10);
        criteria.setSortingMethod(SearchSortingMethods.BEST_MATCH);
        criteria.setPriceLevel(SearchPriceLevel.MODERATE);
        criteria.setName("Italian Restaurant");

        // Create an instance of YelpURIs
        YelpURIs yelpURIs = new YelpURIs();

        // Get the restaurant URI with criteria
        String restaurantURI = yelpURIs.getRestaurantURIWithCriteria(criteria);

        // Verify the generated URI matches the expected format
        String expectedURI = "https://api.yelp.com/v3/businesses/search?location=New York&limit=10&sort_by=best_match&price=2&term=Italian%20Restaurant";
        assertEquals(expectedURI, restaurantURI, "Restaurant URI with criteria should match");
    }

    @Test
    public void testGetRestaurantURIByID() {
        // Restaurant ID for testing
        String restaurantID = "abc123";

        // Create an instance of YelpURIs
        YelpURIs yelpURIs = new YelpURIs();

        // Get the restaurant URI by ID
        String restaurantURI = yelpURIs.getRestaurantURIByID(restaurantID);

        // Verify the generated URI matches the expected format
        String expectedURI = "https://api.yelp.com/v3/businesses/abc123";
        assertEquals(expectedURI, restaurantURI, "Restaurant URI by ID should match");
    }

    @Test
    public void testGetReviewsURI() {
        // Create a ReviewCriteria object for testing
        ReviewCriteria reviewCriteria = new ReviewCriteria.Builder().build();
        reviewCriteria.setRestaurantID("xyz789");
        reviewCriteria.setLimit(5);
        reviewCriteria.setSortingMethod(ReviewSortingMethods.NEWEST);

        // Create an instance of YelpURIs
        YelpURIs yelpURIs = new YelpURIs();

        // Get the reviews URI with criteria
        String reviewsURI = yelpURIs.getReviewsURI(reviewCriteria);

        // Verify the generated URI matches the expected format
        String expectedURI = "https://api.yelp.com/v3/businesses/xyz789/reviews?limit=5&sort_by=NEWEST";
        assertEquals(expectedURI, reviewsURI, "Reviews URI with criteria should match");
    }
}
