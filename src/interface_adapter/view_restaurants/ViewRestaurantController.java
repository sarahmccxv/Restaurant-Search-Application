package interface_adapter.view_restaurants;

import use_case.view_restaurant.ViewRestaurantInputBoundary;
import use_case.view_restaurant.ViewRestaurantInputData;

public class ViewRestaurantController {
    final ViewRestaurantInputBoundary viewRestaurantsInteractor;

    public ViewRestaurantController(ViewRestaurantInputBoundary viewRestaurantsInteractor) {
        this.viewRestaurantsInteractor = viewRestaurantsInteractor;
    }

    public void execute() {
        viewRestaurantsInteractor.execute();
    }
}
