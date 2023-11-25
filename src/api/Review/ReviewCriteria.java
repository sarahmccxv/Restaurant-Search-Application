package api.Review;

public class ReviewCriteria {
    private int limit;
    private String restaurantID;
    private ReviewSortingMethods sortingMethod;

    public ReviewCriteria(Builder builder) {
        this.restaurantID = builder.restaurantID;
        this.limit = builder.limit;
        this.sortingMethod = builder.sortingMethod;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSortingMethod() {
        return sortingMethod.toString();
    }

    public void setSortingMethod(ReviewSortingMethods sortingMethod) {
        this.sortingMethod = sortingMethod;
    }

    public static class Builder {
        private int limit = 3;
        private String restaurantID;
        private ReviewSortingMethods sortingMethod = ReviewSortingMethods.AUTOMATIC;

        public Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder setSortingMethod(ReviewSortingMethods sortingMethod) {
            this.sortingMethod = sortingMethod;
            return this;
        }

        public Builder setRestaurantID(String restaurantID) {
            this.restaurantID = restaurantID;
            return this;
        }

        public ReviewCriteria build() {
            return new ReviewCriteria(this);
        }
    }
}