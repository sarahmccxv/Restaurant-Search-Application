package use_case.view_restaurant;

import use_case.search_restaurant.SearchResaturantInputData;

public interface ViewRestaurantInputBoundary {
    void execute(ViewRestaurantInputData viewRestaurantInputData);
}
