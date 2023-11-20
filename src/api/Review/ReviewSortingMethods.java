package api.Review;

import java.util.ArrayList;

public enum ReviewSortingMethods {
    AUTOMATIC("Best"),
    NEWEST("New");

    public final String description;

    ReviewSortingMethods(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static ArrayList<String> getSortingMethods() {
        ArrayList<String> sortingMethods = new ArrayList<>();
        for (ReviewSortingMethods method : ReviewSortingMethods.values()) {
            sortingMethods.add(method.description);
        }
        return sortingMethods;
    }
}
