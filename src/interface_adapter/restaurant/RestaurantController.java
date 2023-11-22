package interface_adapter.restaurant;

import use_case.restaurant.RestaurantInputBoundary;
import use_case.restaurant.RestaurantInputData;

public class RestaurantController {
    final RestaurantInputBoundary restaurantsInteractor;

    public RestaurantController(RestaurantInputBoundary RestaurantsInteractor) {
        this.restaurantsInteractor = RestaurantsInteractor;
    }

    public void execute(Integer userID, String username, String password, String restaurantID, String previous_view) {
        RestaurantInputData RestaurantInputData = new RestaurantInputData(userID, username, password,
                restaurantID, previous_view);
        //System.out.println("This is Restaurant Controller. I received restaurant ID: " + restaurantID + " and gonna pass to interactor");
        //System.out.println("I also receive userID: " + userID);
        restaurantsInteractor.execute(RestaurantInputData);
    }
}
