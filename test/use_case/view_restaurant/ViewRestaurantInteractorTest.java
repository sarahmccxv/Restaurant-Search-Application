package use_case.view_restaurant;

import data_access.APIRestaurantDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterUserDataAccessInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ViewRestaurantInteractorTest {
    String userID = "336633";
    String username = "Harry";
    String password = "123";
    String location = "Toronto";
    @Test
    void execute() {
        ViewRestaurantDataAccessInterface restaurantRespository = new APIRestaurantDataAccessObject();
        RegisterUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(new User(userID, username, password, location, LocalDateTime.now()));

        ViewRestaurantOutputBoundary successPresenter = new ViewRestaurantOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewRestaurantOutputData viewRestaurantOutputData) {
                assertNotNull(viewRestaurantOutputData.getLocalRestaurants());
                assertEquals(viewRestaurantOutputData.getUserID(), userID);
                assertEquals(viewRestaurantOutputData.getUsername(), username);
                assertEquals(viewRestaurantOutputData.getPassword(), password);
                assertEquals(viewRestaurantOutputData.getLocation(), location);
            }
        };

        ViewRestaurantInputData viewRestaurantInputData = new ViewRestaurantInputData(userID, username, password,
                location);
        ViewRestaurantInputBoundary interactor = new ViewRestaurantInteractor(successPresenter, restaurantRespository,
                userRepository);
        interactor.execute(viewRestaurantInputData);
    }
}
