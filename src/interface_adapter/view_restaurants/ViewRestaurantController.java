package interface_adapter.view_restaurants;

import use_case.view_restaurant.ViewRestaurantInputBoundary;
import use_case.view_restaurant.ViewRestaurantInputData;

public class ViewRestaurantController {
    final ViewRestaurantInputBoundary viewRestaurantsInteractor;

    public ViewRestaurantController(ViewRestaurantInputBoundary viewRestaurantsInteractor) {
        this.viewRestaurantsInteractor = viewRestaurantsInteractor;
    }

    public void execute(Integer userID, String username, String password, String location) {
        ViewRestaurantInputData viewRestaurantInputData = new ViewRestaurantInputData(
                userID, username, password, location);
        viewRestaurantsInteractor.execute(viewRestaurantInputData);
    }
}
