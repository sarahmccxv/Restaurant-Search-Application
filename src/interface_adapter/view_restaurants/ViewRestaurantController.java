package interface_adapter.view_restaurants;

import use_case.view_restaurant.ViewRestaurantInputBoundary;
import use_case.view_restaurant.ViewRestaurantInputData;

public class ViewRestaurantController {
    final ViewRestaurantInputBoundary viewRestaurantsInteractor;

    public ViewRestaurantController(ViewRestaurantInputBoundary viewRestaurantsInteractor) {
        this.viewRestaurantsInteractor = viewRestaurantsInteractor;
    }

    public void execute(Integer userID, String username, String password) {
        ViewRestaurantInputData viewRestaurantInputData = new ViewRestaurantInputData(
                userID, username, password);
        viewRestaurantsInteractor.execute(viewRestaurantInputData);
    }
}
