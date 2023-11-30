package use_case.write_review;

import entity.Restaurant;
import entity.Review;
import entity.ReviewFactory;
import entity.User;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.restaurant.RestaurantOutputBoundary;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import java.time.LocalDateTime;
import java.util.Random;

public class WriteReviewInteractor implements WriteReviewInputBoundary {
    private final WriteReviewDataAccessInterface writeReviewDataAccessObject;
    private final WriteReviewOutputBoundary writeReviewPresenter;
    private ReviewFactory reviewFactory;

    public WriteReviewInteractor(
                                 WriteReviewDataAccessInterface writeReviewDataAccessObject,
                                 WriteReviewOutputBoundary writeReviewPresenter,
                                 ReviewFactory reviewFactory) {
        this.writeReviewDataAccessObject = writeReviewDataAccessObject;
        this.writeReviewPresenter = writeReviewPresenter;
        this.reviewFactory = reviewFactory;
    }

    @Override
    public void execute(WriteReviewInputData writeReviewInputData) {
        User user = writeReviewInputData.getUser();
        Restaurant restauarnt = writeReviewInputData.getRestaurant();
        System.out.println("This is write review interactor. I got input data with user " + user.getUsername() +
                "and restaurant " + restauarnt.getRestaurantName());
        String content = writeReviewInputData.getContent();
        String rating_txt = writeReviewInputData.getRating();
        WriteReviewOutputData writeReviewOutputData = new WriteReviewOutputData(user, restauarnt);
        // check if rating is legit
        if (isValidRating(rating_txt)) {
            Float rating = Float.parseFloat(rating_txt);
            System.out.println("The rating here has string: " + rating_txt);
            String reviewID = String.valueOf(createUserID());
            LocalDateTime now = LocalDateTime.now();
            Review review = reviewFactory.create(reviewID, user, restauarnt.getRestaurantID(), rating, content, now);
            writeReviewDataAccessObject.save(review);
            writeReviewPresenter.prepareSuccessView(writeReviewOutputData);
        } else {
            writeReviewPresenter.prepareFailureView(writeReviewOutputData);
        }
    }

    private Integer createUserID() {
        Random random = new Random();
        int reviewID = 1000000 + random.nextInt(9000000);
        // Check if this random number is already taken
        if (!writeReviewDataAccessObject.existsByID(String.valueOf(reviewID))) {
            return reviewID;
        }
        else {
            return createUserID();
        }
    }

    private boolean isValidRating(String rating_txt) {
        if (rating_txt.matches("^([1-4](\\\\.0|\\\\.5)?)|5(\\\\.0)?$")) {
            try {
                Float rating = Float.parseFloat(rating_txt);
                return rating > 0 && rating <= 5;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
