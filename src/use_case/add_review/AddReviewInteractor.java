package use_case.add_review;

import data_access.FileUserDataAccessObject;
import entity.Restaurant;
import entity.User;
import use_case.register.RegisterUserDataAccessInterface;

public class AddReviewInteractor implements AddReviewInputBoundary {
    private final AddReviewOutputBoundary addReviewPresenter;
    private final RegisterUserDataAccessInterface fileUserDataAccessObject;

    public AddReviewInteractor(AddReviewOutputBoundary addReviewPresenter,
                               RegisterUserDataAccessInterface fileUserDataAccessObject) {
        this.addReviewPresenter = addReviewPresenter;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
    }
    @Override
    public void execute(AddReviewInputData addReviewInputData) {
        String userID = addReviewInputData.getUserID();
        User user = fileUserDataAccessObject.getByUserID(userID);
        Restaurant restaurant = addReviewInputData.getRestaurant();
        AddReviewOutputData addReviewOutputData = new AddReviewOutputData(user, restaurant);
        addReviewPresenter.prepareSuccessView(addReviewOutputData);
    }
}
