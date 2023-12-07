package use_case.view_favourites;

import data_access.FileFavouritesDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.Restaurant;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterUserDataAccessInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ViewFavouritesInteractorTest {

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
                "sarah:iGEvDk6hsizigmXhDKs2Vg,eD-XLSo-j8uIoTnIlrG8YA";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test logic
        ViewFavouritesDataAccessInterface favouritesDAO = new FileFavouritesDataAccessObject("./favourites.csv");
        RegisterUserDataAccessInterface userDAO = new FileUserDataAccessObject("./users.csv", new UserFactory());
        ViewFavouritesInputData inputData = new ViewFavouritesInputData("sarah");
        ViewFavouritesOutputBoundary successPresenter = new ViewFavouritesOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewFavouritesOutputData outputData) {
                assertEquals(outputData.getUsername(), "sarah");
                assertEquals(outputData.getPassword(), "choi");
                assertEquals(outputData.getUserID(), "360619");
                ArrayList<String> restaurantIDs = new ArrayList<>();
                restaurantIDs.add("iGEvDk6hsizigmXhDKs2Vg");
                restaurantIDs.add("eD-XLSo-j8uIoTnIlrG8YA");
                for (Restaurant restaurant: outputData.getFavouritesList()) {
                    assertTrue(restaurantIDs.contains(restaurant.getRestaurantID()));
                }
            }
            @Override
            public void prepareFailView(String username, String password, String noFavouritesMessage) {
                fail("Use case failure is unexpected.");
            }
        };
        ViewFavouritesInputBoundary interator = new ViewFavouritesInteractor(favouritesDAO, successPresenter, userDAO);
        interator.execute(inputData);

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
    void failNoFavouritesTest() throws IOException {
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
        File file = new File(filePath);
        file.createNewFile();

        // Test logic
        ViewFavouritesDataAccessInterface favouritesDAO = new FileFavouritesDataAccessObject("./favourites.csv");
        RegisterUserDataAccessInterface userDAO = new FileUserDataAccessObject("./users.csv", new UserFactory());
        ViewFavouritesInputData inputData = new ViewFavouritesInputData("sarah");
        ViewFavouritesOutputBoundary successPresenter = new ViewFavouritesOutputBoundary() {
            @Override
            public void prepareSuccessView(ViewFavouritesOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String username, String password, String noFavouritesMessage) {
                assertEquals(username, "sarah");
                assertEquals(password, "choi");
                assertEquals(noFavouritesMessage, "No Favourites");
            }
        };
        ViewFavouritesInputBoundary interator = new ViewFavouritesInteractor(favouritesDAO, successPresenter, userDAO);
        interator.execute(inputData);

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