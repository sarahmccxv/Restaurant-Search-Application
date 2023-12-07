package api_testing.SearchTesting;

import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchPriceLevelTest {

    private SearchCriteria.Builder builder;

    @BeforeEach
    void setUp() {
        builder = new SearchCriteria.Builder();
    }

    @Test
    void setName() {
        SearchCriteria criteria = builder.setName("Test").build();
        assertEquals("Test", criteria.getName());
    }

    @Test
    void setLocation() {
        SearchCriteria criteria = builder.setLocation("Location").build();
        assertEquals("Location", criteria.getLocation());
    }

    @Test
    void setLimit() {
        SearchCriteria criteria = builder.setLimit(10).build();
        assertEquals(10, criteria.getLimit());
    }

    @Test
    void setSortingMethod() {
        SearchCriteria criteria = builder.setSortingMethod(SearchSortingMethods.BEST_MATCH).build();
        assertEquals("best_match", criteria.getSortingMethod());
    }

    @Test
    void setPriceLevel() {
        SearchCriteria criteria = builder.setPriceLevel(SearchPriceLevel.MODERATE).build();
        assertEquals("2", criteria.getPriceLevel());
    }

    @Test
    void setCategory() {
        SearchCriteria criteria = builder.setCategory("Category").build();
        assertEquals("Category", criteria.getCategory());
    }

    @Test
    void constructorTest() {
        SearchCriteria criteria = new SearchCriteria.Builder()
                .setName("Name")
                .setLocation("Location")
                .setLimit(10)
                .setSortingMethod(SearchSortingMethods.BEST_MATCH)
                .setPriceLevel(SearchPriceLevel.CHEAP)
                .setCategory("Category")
                .build();

        assertEquals("Name", criteria.getName());
        assertEquals("Location", criteria.getLocation());
        assertEquals(10, criteria.getLimit());
        assertEquals("best_match", criteria.getSortingMethod());
        assertEquals("1", criteria.getPriceLevel());
        assertEquals("Category", criteria.getCategory());
    }

    @Test
    void categoryIsTrimmedAndLowercase() {
        SearchCriteria criteria = builder.setCategory(" Category ").build();
        assertEquals(" Category ", criteria.getCategory());
    }

    @Test
    public void searchPriceLevelBasicTest() {
        assertEquals(SearchPriceLevel.CHEAP.level , "1");
        assertEquals(SearchPriceLevel.CHEAP.range, "Under $15");
    }

    @Test
    public void getPriceLevelsTest() {
        ArrayList<String> expectedRanges = new ArrayList<>(Arrays.asList(
                "Under $15", "$15-$40", "$40-$60", "Above $60"));

        ArrayList<String> actualRanges = SearchPriceLevel.getPriceLevels();

        assertEquals(expectedRanges.size(), actualRanges.size(), "Size mismatch");

        for (int i = 0; i < expectedRanges.size(); i++) {
            assertEquals(expectedRanges.get(i), actualRanges.get(i),
                    "Mismatch at index " + i);
        }
    }
}
