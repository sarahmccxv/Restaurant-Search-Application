package data_access;

import entity.Review;
import entity.ReviewFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.write_review.WriteReviewDataAccessInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FileReviewDataAccessObjectTest {

    private final String TEST_CSV_PATH = "./reviews.csv";  // Provide a test CSV file path
    private final ReviewFactory reviewFactory = new ReviewFactory();
    private final RegisterUserDataAccessInterface fileUserDataAccessObject = new FileUserDataAccessObject("test_users.csv", new UserFactory());

    public FileReviewDataAccessObjectTest() throws IOException {
    }

    @Test
    public void testSaveAndRetrieveReview() throws IOException {
        FileReviewDataAccessObject reviewDataAccessObject = new FileReviewDataAccessObject(TEST_CSV_PATH, reviewFactory, fileUserDataAccessObject);

        // Create a new user for the review
        User newUser = fileUserDataAccessObject.getByUsername("testuser");

        // Create a new review
        Review newReview = reviewFactory.create("123", newUser, "restaurant123", 4.5f, "Great experience!", LocalDateTime.now());

        // Save the review
        reviewDataAccessObject.save(newReview);

        // Retrieve the review by reviewID
        Review retrievedReview = reviewDataAccessObject.getByReviewID("123");

        assertNotNull(retrievedReview);
        assertEquals(newUser, retrievedReview.getAuthor());
        assertEquals("restaurant123", retrievedReview.getRestaurantID());
        assertEquals(4.5f, retrievedReview.getRating());
        assertEquals("Great experience!", retrievedReview.getContent());

        // Clean up: remove the test CSV file
        Path testCsvPath = Paths.get(TEST_CSV_PATH);
        if (testCsvPath.toFile().exists()) {
            assertTrue(testCsvPath.toFile().delete());
        }
    }

    @Test
    public void testExistsByID() throws IOException {
        FileReviewDataAccessObject reviewDataAccessObject = new FileReviewDataAccessObject(TEST_CSV_PATH, reviewFactory, fileUserDataAccessObject);

        // Add a review
        Review newReview = reviewFactory.create("456", fileUserDataAccessObject.getByUsername("existinguser"), "restaurant456", 3.8f, "Good service!", LocalDateTime.now());
        reviewDataAccessObject.save(newReview);

        // Check if the review exists by reviewID
        assertTrue(reviewDataAccessObject.existsByID("456"));

        // Check if a non-existing review returns false
        assertFalse(reviewDataAccessObject.existsByID("nonexistingreview"));

        // Clean up: remove the test CSV file
        Path testCsvPath = Paths.get(TEST_CSV_PATH);
        if (testCsvPath.toFile().exists()) {
            assertTrue(testCsvPath.toFile().delete());
        }
    }

    // Add more test cases for other methods as needed
}
