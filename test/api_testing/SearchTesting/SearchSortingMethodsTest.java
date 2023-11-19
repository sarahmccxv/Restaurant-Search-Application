package api_testing.SearchTesting;

import static org.junit.jupiter.api.Assertions.*;

import api.Search.SearchSortingMethods;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchSortingMethodsTest {

    @Test
    public void searchSortingMethodsBasicTest() {
        assertEquals(SearchSortingMethods.BEST_MATCH.toString(), "best_match");
        assertEquals(SearchSortingMethods.BEST_MATCH.description, "Best Match");
    }

    @Test
    public void getSortingMethodsTest() {
        ArrayList<String> expectedMethods = new ArrayList<>(Arrays.asList(
                "Best Match", "Highest Rated", "Most Reviewed", "Closest"));

        ArrayList<String> actualMethods = SearchSortingMethods.BEST_MATCH.getSortingMethods();
        assertEquals(expectedMethods.size(), actualMethods.size(), "Size mismatch");

        for (int i = 0; i < expectedMethods.size(); i++) {
            assertEquals(expectedMethods.get(i), actualMethods.get(i),
                    "Mismatch at index " + i);
        }
    }
}
