package api.Search;

import java.util.ArrayList;

public enum SearchPriceLevel {
    CHEAP("1", "Under $15"),
    MODERATE("2", "$15-$40"),
    PRICEY("3", "$40-$60"),
    LUXURY("4", "Above $60");

    public final String level;
    public final String range;

    SearchPriceLevel(String level, String range) {
        this.level = level;
        this.range = range;
    }

    public ArrayList<String> getPriceLevels() {
        ArrayList<String> sortingMethods = new ArrayList<>();
        for (SearchPriceLevel price : SearchPriceLevel.values()) {
            sortingMethods.add(price.range);
        }
        return sortingMethods;
    }
}
