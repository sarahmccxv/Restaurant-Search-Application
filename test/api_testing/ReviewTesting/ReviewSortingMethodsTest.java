package api_testing.ReviewTesting;

import api.Review.ReviewCriteria;
import api.Review.ReviewSortingMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewSortingMethodsTest {

    private ReviewCriteria.Builder builder;

    @BeforeEach
    void setUp() {
        builder = new ReviewCriteria.Builder();
    }

    @Test
    void setLimit() {
        ReviewCriteria criteria = builder.setLimit(5).build();
        assertEquals(5, criteria.getLimit());
    }

    @Test
    void setSortingMethod() {
        ReviewCriteria criteria = builder.setSortingMethod(ReviewSortingMethods.NEWEST).build();
        assertEquals("NEWEST", criteria.getSortingMethod());
    }

    @Test
    void setRestaurantID() {
        ReviewCriteria criteria = builder.setRestaurantID("ID123").build();
        assertEquals("ID123", criteria.getRestaurantID());
    }

    @Test
    void constructorTest() {
        ReviewCriteria criteria = new ReviewCriteria.Builder()
                .setLimit(7)
                .setSortingMethod(ReviewSortingMethods.AUTOMATIC)
                .setRestaurantID("ID456")
                .build();

        assertEquals(7, criteria.getLimit());
        assertEquals("AUTOMATIC", criteria.getSortingMethod());
        assertEquals("ID456", criteria.getRestaurantID());
    }

    // Test default values
    @Test
    void defaultValues() {
        ReviewCriteria criteria = builder.build();
        assertEquals(3, criteria.getLimit());
        assertEquals("AUTOMATIC", criteria.getSortingMethod());
    }

    @Test
    public void testAutomaticDescription() {
        ReviewSortingMethods method = ReviewSortingMethods.AUTOMATIC;
        assertEquals("Best", method.description);
    }

    @Test
    public void testNewestDescription() {
        ReviewSortingMethods method = ReviewSortingMethods.NEWEST;
        assertEquals("New", method.description);
    }

    @Test
    public void testGetSortingMethods() {
        ArrayList<String> expectedMethods = new ArrayList<>(Arrays.asList("Best", "New"));
        ArrayList<String> actualMethods = ReviewSortingMethods.getSortingMethods();
        assertEquals(expectedMethods, actualMethods);
    }
}