package api.Search;

import java.util.ArrayList;

public class SearchCriteria {
    private final String name;
    private final String location;
    private final int limit;
    private final SearchSortingMethods sortingMethod;
    private final SearchPriceLevel priceLevel;
    private final ArrayList<String> category;

    public SearchCriteria(Builder builder) {
        this.name = builder.name;
        this.location = builder.location;
        this.limit = builder.limit;
        this.sortingMethod = builder.sortingMethod;
        this.category = builder.category;
        this.priceLevel = builder.priceLevel;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getLimit() {
        return limit;
    }

    public String getSortingMethod() {
        return sortingMethod.toString();
    }

    public String getPriceLevel() {
        return priceLevel.level;
    }

    public ArrayList<String> getCategory() {
        return category;
    }

    public static class Builder {
        private String name;
        private String location = "";
        private int limit = 5;
        private SearchSortingMethods sortingMethod = SearchSortingMethods.BEST_MATCH;
        private SearchPriceLevel priceLevel = SearchPriceLevel.CHEAP;
        private ArrayList<String> category;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder setSortingMethod(SearchSortingMethods sortingMethod) {
            this.sortingMethod = sortingMethod;
            return this;
        }

        public Builder setPriceLevel(SearchPriceLevel priceLevel) {
            this.priceLevel = priceLevel;
            return this;
        }

        public Builder setCategory(ArrayList<String> category) {
            this.category = category;
            return this;
        }

        public SearchCriteria build() {
            return new SearchCriteria(this);
        }
    }
}