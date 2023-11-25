package use_case.sortandfilter;

public interface SortAndFilterRestaurantOutputBoundary {
    void prepareSuccessView(SortAndFilterRestaurantOutputData sortAndFilterRestaurantOutputData);
    void prepareFailView(String error);
}
