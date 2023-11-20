package api_testing.SearchTesting;

import api.Search.SearchPriceLevel;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchPriceLevelTest {

    @Test
    public void searchPriceLevelBasicTest() {
        assertEquals(SearchPriceLevel.CHEAP.level , "1");
        assertEquals(SearchPriceLevel.CHEAP.range, "Under $15");
    }

    @Test
    public void getPriceLevelsTest() {
        ArrayList<String> expectedRanges = new ArrayList<>(Arrays.asList(
                "Under $15", "$15-$40", "$40-$60", "Above $60"));

        ArrayList<String> actualRanges = SearchPriceLevel.CHEAP.getPriceLevels();

        assertEquals(expectedRanges.size(), actualRanges.size(), "Size mismatch");

        for (int i = 0; i < expectedRanges.size(); i++) {
            assertEquals(expectedRanges.get(i), actualRanges.get(i),
                    "Mismatch at index " + i);
        }
    }
}
