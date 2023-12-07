package data_access_testing;

import data_access.FileUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FileUserDataAccessObjectTest {

    private final String testFilePath = "test_users.csv";
    private final UserFactory userFactory = new UserFactory();

    @BeforeEach
    @AfterEach
    public void cleanUp() {
        File testFile = new File(testFilePath);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testEmptyFileInitialization() {
        FileUserDataAccessObject userDataAccessObject = null;
        try {
            userDataAccessObject = new FileUserDataAccessObject(testFilePath, userFactory);
        } catch (IOException e) {
            fail("Initialization with an empty file should not throw an exception.");
        }
        assertNotNull(userDataAccessObject, "Initialization with an empty file should not result in null object.");
        // Add assertions to check the object state after initialization
    }

    @Test
    public void testSaveAndGetByUsername() {
        FileUserDataAccessObject userDataAccessObject = null;
        try {
            userDataAccessObject = new FileUserDataAccessObject(testFilePath, userFactory);
        } catch (IOException e) {
            fail("Initialization with an empty file should not throw an exception.");
        }
        assertNotNull(userDataAccessObject);

        User user = userFactory.create("1", "username", "password", "location", LocalDateTime.now());
        userDataAccessObject.save(user);

        User retrievedUser = userDataAccessObject.getByUsername("username");
        assertNotNull(retrievedUser);
        assertEquals("1", retrievedUser.getUserID());
        assertEquals("username", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
        assertEquals("location", retrievedUser.getLocation());
        assertNotNull(retrievedUser.getCreationTime());
    }
}
