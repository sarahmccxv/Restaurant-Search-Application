package data_access;

import data_access.FileUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.user_profile.UserProfileDataAccessInterface;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class FileUserDataAccessObjectTest {

    private final String TEST_CSV_PATH = "./users.csv";  // Provide a test CSV file path
    private final UserFactory userFactory = new UserFactory();

    @Test
    public void testSaveAndRetrieveUser() throws IOException {
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(TEST_CSV_PATH, userFactory);

        // Create a new user
        User newUser = userFactory.create("123", "testuser", "testpassword", "testlocation", LocalDateTime.now());

        // Save the user
        userDataAccessObject.save(newUser);

        // Retrieve the user by username
        User retrievedUser = userDataAccessObject.getByUsername("testuser");

        assertNotNull(retrievedUser);
        assertEquals("123", retrievedUser.getUserID());
        assertEquals("testpassword", retrievedUser.getPassword());
        assertEquals("testlocation", retrievedUser.getLocation());

        // Clean up: remove the test CSV file
        Path testCsvPath = Paths.get(TEST_CSV_PATH);
        if (testCsvPath.toFile().exists()) {
            assertTrue(testCsvPath.toFile().delete());
        }
    }

    @Test
    public void testExistsByName() throws IOException {
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(TEST_CSV_PATH, userFactory);

        // Add a user
        User newUser = userFactory.create("456", "existinguser", "existingpassword", "existinglocation", LocalDateTime.now());
        userDataAccessObject.save(newUser);

        // Check if the user exists by username
        assertTrue(userDataAccessObject.existsByName("existinguser"));

        // Check if a non-existing user returns false
        assertFalse(userDataAccessObject.existsByName("nonexistinguser"));

        // Clean up: remove the test CSV file
        Path testCsvPath = Paths.get(TEST_CSV_PATH);
        if (testCsvPath.toFile().exists()) {
            assertTrue(testCsvPath.toFile().delete());
        }
    }
    @Test
    public void testUpdate() throws IOException {
        // Initialize the FileUserDataAccessObject with a test CSV file
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(TEST_CSV_PATH, userFactory);

        // Simulate an update in the CSV file by adding a new user
        String newUserData = "123,newuser,newpassword,newlocation,2023-01-01T12:00:00";
        appendToFile(TEST_CSV_PATH, newUserData);

        // Perform the update
        userDataAccessObject.update();

        // Retrieve the newly added user
        User newUser = userDataAccessObject.getByUsername("newuser");

        assertNotNull(newUser);
        assertEquals("123", newUser.getUserID());
        assertEquals("newpassword", newUser.getPassword());
        assertEquals("newlocation", newUser.getLocation());

        // Clean up: remove the test CSV file
        Path testCsvPath = Paths.get(TEST_CSV_PATH);
        if (testCsvPath.toFile().exists()) {
            assertTrue(testCsvPath.toFile().delete());
        }
    }

    @Test
    public void testUpdateUserInfo() throws IOException {
        // Initialize the FileUserDataAccessObject with a test CSV file
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(TEST_CSV_PATH, userFactory);

        // Create a new user
        User newUser = userFactory.create("456", "testuser", "testpassword", "testlocation", LocalDateTime.now());

        // Save the user
        userDataAccessObject.save(newUser);

        // Modify the user's information
        newUser.setPassword("newpassword");
        newUser.setLocation("newlocation");

        // Perform the update
        userDataAccessObject.updateUserInfo(newUser);

        // Retrieve the updated user
        User updatedUser = userDataAccessObject.getByUserID("456");

        assertNotNull(updatedUser);
        assertEquals("newpassword", updatedUser.getPassword());
        assertEquals("newlocation", updatedUser.getLocation());

        // Clean up: remove the test CSV file
        Path testCsvPath = Paths.get(TEST_CSV_PATH);
        if (testCsvPath.toFile().exists()) {
            assertTrue(testCsvPath.toFile().delete());
        }
    }

    // Helper method to append data to a file
    private void appendToFile(String filePath, String data) throws IOException {
        java.nio.file.Files.write(Paths.get(filePath), (data + System.lineSeparator()).getBytes(), java.nio.file.StandardOpenOption.APPEND);
    }

}
