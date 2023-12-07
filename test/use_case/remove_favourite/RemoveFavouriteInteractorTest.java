package use_case.remove_favourite;

import data_access.APIRestaurantDataAccessObject;
import data_access.FileFavouritesDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.FavouritesList;
import entity.Restaurant;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class RemoveFavouriteInteractorTest {

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
        RemoveFavouriteDataAccessInterface removeFavouritesDAO = new FileFavouritesDataAccessObject("favourites.csv");
        ViewFavouritesDataAccessInterface favouritesDAO = new FileFavouritesDataAccessObject("favourites.csv");
        RegisterUserDataAccessInterface userDAO = new FileUserDataAccessObject("./users.csv", new UserFactory());
        String restaurantID = "iGEvDk6hsizigmXhDKs2Vg";
        Restaurant restaurant = new APIRestaurantDataAccessObject().getRestaurantByID(restaurantID);
        FavouritesList newFavouritesList = favouritesDAO.getFavouritesList("sarah");
        newFavouritesList.remove(restaurantID);
        RemoveFavouriteInputData inputData = new RemoveFavouriteInputData("sarah", restaurant);
        RemoveFavouriteOutputBoundary successPresenter = new RemoveFavouriteOutputBoundary() {
            @Override
            public void prepareSuccessView(RemoveFavouriteOutputData outputData) {
                assertEquals(outputData.getMessage(), restaurant.getRestaurantName() + " has been removed from favourites.");
                FavouritesList expected = new FavouritesList();
                expected.add(new APIRestaurantDataAccessObject().getRestaurantByID("eD-XLSo-j8uIoTnIlrG8YA"));
                assertEquals(newFavouritesList, expected);
                assertEquals(newFavouritesList, outputData.getNewFavouritesList());
            }
        };
        RemoveFavouriteInputBoundary interactor = new RemoveFavouriteInteractor(userDAO, removeFavouritesDAO, successPresenter);
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

