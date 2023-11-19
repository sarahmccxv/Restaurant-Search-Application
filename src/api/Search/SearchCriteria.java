package api.Search;

public class SearchCriteria {
    private String location;
    private int limit;
    private String sortingMethod;
    private String category;

    public SearchCriteria(Builder builder) {
        this.location = builder.location;
        this.limit = builder.limit;
        this.sortingMethod = builder.sortingMethod;
        this.category = builder.category;
    }

    public String getLocation() {
        return location;
    }

    public int getLimit() {
        return limit;
    }

    public String getSortingMethod() {
        return sortingMethod;
    }

    public String getCategory() {
        return category;
    }

    public static class Builder {
        private String location = "";
        private int limit = 10;
        private String sortingMethod = "best_match";
        private String category = "";

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder setSortingMethod(String sortingMethod) {
            this.sortingMethod = sortingMethod;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public SearchCriteria build() {
            return new SearchCriteria(this);
        }
    }
}