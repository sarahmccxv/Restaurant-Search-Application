package use_case.add_review;

import data_access.APIRestaurantDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.Restaurant;
import entity.User;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddReviewInteractorTest {
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

        AddReviewOutputBoundary presenter = new AddReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(AddReviewOutputData addReviewOutputData) {
                assertEquals(addReviewOutputData.getUser().getUserID(), userID);
                assertEquals(addReviewOutputData.getUser().getUsername(), username);
                assertEquals(addReviewOutputData.getUser().getPassword(), password);
                assertEquals(addReviewOutputData.getUser().getLocation(), location);
                assertEquals(addReviewOutputData.getRestaurant().getRestaurantID(), restaurantID);
            }
        };

        Restaurant restaurant = restaurantRespository.getRestaurantByID(restaurantID);
        AddReviewInputData inputdata = new AddReviewInputData(userID, restaurant, previousView);
        AddReviewInputBoundary interactor = new AddReviewInteractor(presenter, userRepository);
        interactor.execute(inputdata);
    }
}
