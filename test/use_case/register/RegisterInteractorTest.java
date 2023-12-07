package use_case.register;

import api.yelp.YelpApiServices;
import data_access.APIRestaurantDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RegisterInteractorTest {
    @Test
    void successTest() {
        RegisterInputData inputData = new RegisterInputData("Paul", "password", "password", "Toronto");
        RegisterUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        RegisterOutputBoundary successPresenter = new RegisterOutputBoundary() {
            @Override
            public void prepareSuccessView(RegisterOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                assertEquals("Paul", user.getUsername());
                assertNotNull(user.getCreationTime()); // any creation time is fine.
                user.setCreationTime(LocalDateTime.now().toString());
                assertTrue(userRepository.existsByName("Paul"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        YelpApiServices APIRestaurantDataAccessObject = new APIRestaurantDataAccessObject();
        RegisterInputBoundary interactor = new RegisterInteractor(userRepository, APIRestaurantDataAccessObject, successPresenter, new UserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        RegisterInputData inputData = new RegisterInputData("Paul", "password", "wrong", "Toronto");
        RegisterUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        RegisterOutputBoundary failurePresenter = new RegisterOutputBoundary() {
            @Override
            public void prepareSuccessView(RegisterOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        YelpApiServices APIRestaurantDataAccessObject = new APIRestaurantDataAccessObject();
        RegisterInputBoundary interactor = new RegisterInteractor(userRepository, APIRestaurantDataAccessObject, failurePresenter, new UserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserExistsTest() {
        RegisterInputData inputData = new RegisterInputData("Paul", "password", "wrong", "Toronto");
        RegisterUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // Add Paul to the repo so that when we check later they already exist
        UserFactory factory = new UserFactory();
        User user = factory.create("1", "Paul", "pwd",  "Toronto", LocalDateTime.now());
        userRepository.save(user);

        // This creates a presenter that tests whether the test case is as we expect.
        RegisterOutputBoundary failurePresenter = new RegisterOutputBoundary() {
            @Override
            public void prepareSuccessView(RegisterOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists, please use another username.", error);
            }
        };

        YelpApiServices APIRestaurantDataAccessObject = new APIRestaurantDataAccessObject();
        RegisterInputBoundary interactor = new RegisterInteractor(userRepository, APIRestaurantDataAccessObject, failurePresenter, new UserFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureCityNotSupportTest() {
        RegisterInputData inputData = new RegisterInputData("Paul", "password", "password", "111");
        RegisterUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a presenter that tests whether the test case is as we expect.
        RegisterOutputBoundary failurePresenter = new RegisterOutputBoundary() {
            @Override
            public void prepareSuccessView(RegisterOutputData user) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("City currently not supported.", error);
            }
        };

        YelpApiServices APIRestaurantDataAccessObject = new APIRestaurantDataAccessObject();
        RegisterInputBoundary interactor = new RegisterInteractor(userRepository, APIRestaurantDataAccessObject, failurePresenter, new UserFactory());
        interactor.execute(inputData);
    }
}