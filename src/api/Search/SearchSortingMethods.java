package api.Search;

import java.util.ArrayList;

public enum SearchSortingMethods {
    BEST_MATCH("Best Match"),
    RATING("Highest Rated"),
    REVIEW_COUNT("Most Reviewed"),
    DISTANCE("Closest");

    public final String description;

    SearchSortingMethods(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public ArrayList<String> getSortingMethods() {
        ArrayList<String> sortingMethods = new ArrayList<>();
        for (SearchSortingMethods method : SearchSortingMethods.values()) {
            sortingMethods.add(method.description);
        }
        return sortingMethods;
    }
}
