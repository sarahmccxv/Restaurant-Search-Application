package use_case.search_restaurant;

import data_access.APIRestaurantDataAccessObject;
import entity.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchRestaurantInteractorTest {

    @Test
    void execute() {

        SearchRestaurantDataAccessInterface restaurantRespository = new APIRestaurantDataAccessObject();

        SearchRestaurantOutputBoundary successPresenter = new SearchRestaurantOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchRestaurantOutputData resaturantInputData) {
                ArrayList<String> categories1 = new ArrayList<>();
                categories1.add("Chinese");
                categories1.add("Dim Sum");
                categories1.add("Dumplings");
                for (Restaurant restaurant: resaturantInputData.getRestaurants()) {
                    assertTrue(categories1.containsAll(restaurant.getCategories()));
                    assertEquals("china",resaturantInputData.getCategory());
                }
                assertNotNull(resaturantInputData.getCategory());
                assertNotNull(resaturantInputData.getRestaurants());
//                assertTrue(restaurantRespository.getRestaurantByName());
            }

            @Override
            public void prepareFailView(String error) {
            }
        };

        SearchResaturantInputData searchResaturantInputData = new SearchResaturantInputData("Toronto", "china");
        SearchRestaurantInputBoundary interactor = new SearchRestaurantInteractor(restaurantRespository, successPresenter);
        interactor.execute(searchResaturantInputData);
    }
}