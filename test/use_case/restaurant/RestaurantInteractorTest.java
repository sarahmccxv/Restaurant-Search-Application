package use_case.restaurant;

import data_access.APIRestaurantDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestaurantInteractorTest {
    String userID = "336633";
    String username = "Harry";
    String password = "123";
    String location = "Toronto";
    String restaurantID = "iGEvDk6hsizigmXhDKs2Vg";
    String previousView = "view restaurants";
    @Test
    void execute() {
        ViewRestaurantDataAccessInterface restaurantRespository = new APIRestaurantDataAccessObject();
        RegisterUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(new User(userID, username, password, location, LocalDateTime.now()));

        RestaurantOutputBoundary successPresenter = new RestaurantOutputBoundary() {
            @Override
            public void prepareSuccessView(RestaurantOutputData restaurantOutputData) {
                assertNotNull(restaurantOutputData.getRestaurant());
                assertEquals(restaurantOutputData.getRestaurant().getRestaurantID(), restaurantID);
                assertEquals(restaurantOutputData.getUserID(), userID);
                assertEquals(restaurantOutputData.getUsername(), username);
                assertEquals(restaurantOutputData.getPassword(), password);
                assertEquals(restaurantOutputData.getPreviousView(), previousView);
            }
        };

        RestaurantInputData restaurantInputData = new RestaurantInputData(userID, username, password, restaurantID,
                previousView);
        RestaurantInputBoundary interactor = new RestaurantInteractor(userRepository, restaurantRespository,
                successPresenter);
        interactor.execute(restaurantInputData);
    }
}
