package api.Review;

public class ReviewCriteria {
    private final int limit;
    private final ReviewSortingMethods sortingMethod;

    public ReviewCriteria(Builder builder) {
        this.limit = builder.limit;
        this.sortingMethod = builder.sortingMethod;
    }

    public int getLimit() {
        return limit;
    }

    public String getSortingMethod() {
        return sortingMethod.toString();
    }

    public static class Builder {
        private int limit = 3;
        private ReviewSortingMethods sortingMethod = ReviewSortingMethods.AUTOMATIC;

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder setSortingMethod(ReviewSortingMethods sortingMethod) {
            this.sortingMethod = sortingMethod;
            return this;
        }

        public ReviewCriteria build() {
            return new ReviewCriteria(this);
        }
    }
}