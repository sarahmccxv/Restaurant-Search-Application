package use_case.search_restaurant;

public interface SearchRestaurantOutputBoundary {
    void prepareSuccessView(SearchRestaurantOutputData resaturantInputData);
    void prepareFailView(String error);
}
