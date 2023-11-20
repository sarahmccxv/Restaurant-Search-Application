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

    public static ArrayList<String> getPriceLevels() {
        ArrayList<String> priceLevelsList = new ArrayList<>();
        for (SearchPriceLevel price : SearchPriceLevel.values()) {
            priceLevelsList.add(price.range);
        }
        return priceLevelsList;
    }
}
