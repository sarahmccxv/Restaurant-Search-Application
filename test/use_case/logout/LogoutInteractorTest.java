package use_case.logout;

import data_access.APIRestaurantDataAccessObject;
import data_access.FileFavouritesDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.Restaurant;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class LogoutInteractorTest {

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
                "sarah:iGEvDk6hsizigmXhDKs2Vg";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Test logic
        FileFavouritesDataAccessObject favouritesDAO= new FileFavouritesDataAccessObject("favourites.csv");
        FileUserDataAccessObject userDAO = new FileUserDataAccessObject("./users.csv", new UserFactory());
        String username = "sarah";
        LogoutInputData inputData = new LogoutInputData(username);
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView(LogoutOutputData outputData) {
                Restaurant restaurant = new APIRestaurantDataAccessObject().getRestaurantByID("iGEvDk6hsizigmXhDKs2Vg");
                assertEquals(outputData.getUsername(), username);
                assertTrue(userDAO.existsByName("sarah"));
                assertTrue(favouritesDAO.hasFavourite(userDAO.getByUsername("sarah"), restaurant));
            }
        };
        LogoutInputBoundary interactor = new LogoutInteractor(successPresenter);
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