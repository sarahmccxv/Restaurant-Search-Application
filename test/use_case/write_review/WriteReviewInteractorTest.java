package use_case.write_review;

import data_access.APIRestaurantDataAccessObject;
import data_access.FileReviewDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.Restaurant;
import entity.ReviewFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterUserDataAccessInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class WriteReviewInteractorTest {
    private final String userID = "100001";
    private final String restaurantID = "iGEvDk6hsizigmXhDKs2Vg";
    private String restaurantName;
    private String rating;
    private String content;

    private final String userFilePath = "users.csv";
    private final String userFileContent = "userID,username,password,location,creation_time\n" +
            "100001,harry,123,toronto," + LocalDateTime.now().toString();

    @Test
    void successTest() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(userFilePath))) {
            writer.println(userFileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rating = "5";
        content = "good";

        RegisterUserDataAccessInterface fileUserDataAccessObject =
                new FileUserDataAccessObject("./users.csv", new UserFactory());
        WriteReviewDataAccessInterface writeReviewDataAccessObject = new FileReviewDataAccessObject(
                "./reviews.csv", new ReviewFactory(), fileUserDataAccessObject);
        User user = fileUserDataAccessObject.getByUserID(userID);
        Restaurant restaurant = new APIRestaurantDataAccessObject().getRestaurantByID(restaurantID);
        String restaurantName = restaurant.getRestaurantName();

        WriteReviewInputData inputData = new WriteReviewInputData(user, restaurant, rating, content);
        WriteReviewOutputBoundary presenter = new WriteReviewOutputBoundary() {
            @Override
            public void prepareSuccessView(WriteReviewOutputData writeReviewOutputData) {
                assertEquals(writeReviewOutputData.getRestaurant().getRestaurantID(), restaurantID);
                assertEquals(writeReviewOutputData.getUser().getUserID(), userID);
                assertEquals(writeReviewOutputData.getSuccessMessage(),"Review for " + restaurantName +
                        "successfully saved.");
            }

            @Override
            public void prepareFailureView(WriteReviewOutputData writeReviewOutputData) {
                fail("unexpected failure");
            }
        };
        WriteReviewInputBoundary interactor = new WriteReviewInteractor(writeReviewDataAccessObject,
                presenter, new ReviewFactory());
        interactor.execute(inputData);
    }

    @Test
    void failIllegalRating() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(userFilePath))) {
            writer.println(userFileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        rating = "8";
        content = "good";

        RegisterUserDataAccessInterface fileUserDataAccessObject =
                new FileUserDataAccessObject("./users.csv", new UserFactory());
        WriteReviewDataAccessInterface writeReviewDataAccessObject = new FileReviewDataAccessObject(
                "./reviews.csv", new ReviewFactory(), fileUserDataAccessObject);
        User user = fileUserDataAccessObject.getByUserID(userID);
        Restaurant restaurant = new APIRestaurantDataAccessObject().getRestaurantByID(restaurantID);

        WriteReviewInputData inputData = new WriteReviewInputData(user, restaurant, rating, content);
        WriteReviewOutputBoundary presenter = new WriteReviewOutputBoundary() {

            @Override
            public void prepareSuccessView(WriteReviewOutputData writeReviewOutputData) {
                fail("This test should fail due to illegal rating given.");
            }

            @Override
            public void prepareFailureView(WriteReviewOutputData writeReviewOutputData) {
                // Due to illegal rating, this review should not be added
                assertEquals(writeReviewOutputData.getRestaurant().getRestaurantID(), restaurantID);
                assertEquals(writeReviewOutputData.getUser().getUserID(), userID);
                assertEquals(writeReviewOutputData.getFailureMessage(), "Illegal rating given. Please specify rating from 0-5 with 1 decimal place, and the last digit should be 0 or 5.");
            }
        };
        WriteReviewInputBoundary interactor = new WriteReviewInteractor(writeReviewDataAccessObject,
                presenter, new ReviewFactory());
        interactor.execute(inputData);
    }
}
