package api_testing.ReviewTesting;

import api.Review.ReviewSortingMethods;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ReviewSortingMethodsTest {

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