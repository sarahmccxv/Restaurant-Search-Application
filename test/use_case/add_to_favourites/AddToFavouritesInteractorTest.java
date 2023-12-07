package use_case.add_to_favourites;

import data_access.APIRestaurantDataAccessObject;
import data_access.FileFavouritesDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.Restaurant;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterUserDataAccessInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class AddToFavouritesInteractorTest {


    @Test
    void successTest() throws IOException {
        // Setup: Create mock DAOs
        String filePath = "users.csv";
        String fileContent = "userID,username,password,location,creation_time\n" +
                "360619,sarah,choi,toronto,2023-12-05T20:00:15.857851";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePath = "favourites.csv";
        fileContent = "username:favourites\n" +
                "sarah:eD-XLSo-j8uIoTnIlrG8YA";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test logic
        Restaurant restaurant = new APIRestaurantDataAccessObject().getRestaurantByID("iGEvDk6hsizigmXhDKs2Vg");
        AddToFavouritesInputData inputData = new AddToFavouritesInputData("sarah", restaurant);
        AddToFavouritesDataAccessInterface favouritesDAO = new FileFavouritesDataAccessObject("favourites.csv");
        RegisterUserDataAccessInterface userDAO = new FileUserDataAccessObject("./users.csv", new UserFactory());
        AddToFavouritesOutputBoundary successPresenter = new AddToFavouritesOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToFavouritesOutputData addToFavouritesOutputData) {
                assertEquals(addToFavouritesOutputData.getUsername(), "sarah");
                assertEquals(addToFavouritesOutputData.getRestaurantName(), "Seven Lives Tacos y Mariscos");
                assertEquals(addToFavouritesOutputData.getSuccessMessage(), "Seven Lives Tacos y Mariscos" + " was successfully added to favourites.");
                assertTrue(favouritesDAO.hasFavourite(userDAO.getByUsername("sarah"), restaurant));
            }
            @Override
            public void prepareFailView(AddToFavouritesOutputData addToFavouritesOutputData) {
                fail("Use case failure is unexpected.");
            }
        };
        AddToFavouritesInputBoundary interactor = new AddToFavouritesInteractor(userDAO, favouritesDAO, successPresenter);
        interactor.execute(inputData);

        // Teardown: Remove mock DAOs
        Path path = Paths.get("./favourites.csv");
        if (Files.exists(path)) {
            Files.delete(path);
        }
        path = Paths.get("./users.csv");
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    void failAlreadyInFavouritesTest() throws IOException {
        // Setup: Create mock DAOs
        String filePath = "users.csv";
        String fileContent = "userID,username,password,location,creation_time\n" +
                "360619,sarah,choi,toronto,2023-12-05T20:00:15.857851";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePath = "favourites.csv";
        fileContent = "username:favourites\n" +
                "sarah:iGEvDk6hsizigmXhDKs2Vg,eD-XLSo-j8uIoTnIlrG8YA";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test logic
        Restaurant restaurant = new APIRestaurantDataAccessObject().getRestaurantByID("iGEvDk6hsizigmXhDKs2Vg");
        AddToFavouritesInputData inputData = new AddToFavouritesInputData("sarah", restaurant);
        AddToFavouritesDataAccessInterface favouritesDAO = new FileFavouritesDataAccessObject("favourites.csv");
        RegisterUserDataAccessInterface userDAO = new FileUserDataAccessObject("./users.csv", new UserFactory());
        AddToFavouritesOutputBoundary successPresenter = new AddToFavouritesOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToFavouritesOutputData addToFavouritesOutputData) {
                fail("Use case success is unexpected.");
            }
            @Override
            public void prepareFailView(AddToFavouritesOutputData addToFavouritesOutputData) {
                assertEquals(addToFavouritesOutputData.getUsername(), "sarah");
                assertEquals(addToFavouritesOutputData.getRestaurantName(), "Seven Lives Tacos y Mariscos");
                assertEquals(addToFavouritesOutputData.getFailureMessage(), "Seven Lives Tacos y Mariscos" + " already exists in favourites.");
                assertTrue(favouritesDAO.hasFavourite(userDAO.getByUsername("sarah"), restaurant));
            }
        };
        AddToFavouritesInputBoundary interactor = new AddToFavouritesInteractor(userDAO, favouritesDAO, successPresenter);
        interactor.execute(inputData);

        // Teardown: Remove mock DAOs
        Path path = Paths.get("./favourites.csv");
        if (Files.exists(path)) {
            Files.delete(path);
        }
        path = Paths.get("./users.csv");
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

}