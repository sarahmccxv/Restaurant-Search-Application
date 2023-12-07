package use_case.user_profile;

import api.yelp.YelpApiServices;
import entity.User;
import entity.UserFactory;
import interface_adapter.user_profile.UserProfilePresenter;
import use_case.register.RegisterOutputData;

import java.time.LocalDateTime;

public class UserProfileInteractor implements UserProfileInputBoundary{
    final UserProfileOutputBoundary userProfilePresenter;
    final UserProfileDataAccessInterface fileUserDataAccessObject;
    final UserFactory userFactory;
    final YelpApiServices APIRestaurantDataAccessObject;


    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter,
                                 UserProfileDataAccessInterface fileUserDataAccessObject,
                                 YelpApiServices APIRestaurantDataAccessObject,
                                 UserFactory userFactory) {
        this.userProfilePresenter = userProfilePresenter;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.userFactory = userFactory;
        this.APIRestaurantDataAccessObject = APIRestaurantDataAccessObject;
    }

    @Override
    public void execute(UserProfileInputData userProfileInputData) {
        String userID = userProfileInputData.getUserID();
        String username = userProfileInputData.getUsername();
        String password = userProfileInputData.getPassword();
        String location = userProfileInputData.getLocation();

        try{
            APIRestaurantDataAccessObject.getLocalRestaurants(location);

            fileUserDataAccessObject.update();
            User oldUser = fileUserDataAccessObject.getByUserID(userID);
            User newUser = userFactory.create(userID, username, password, location, oldUser.getCreationTime());
            fileUserDataAccessObject.updateUserInfo(newUser);
            UserProfileOutputData userProfileOutputData = new UserProfileOutputData(userID,
                    username, password, location);
            userProfilePresenter.prepareSuccessView(userProfileOutputData);
            } catch (Exception e) {
            userProfilePresenter.prepareFailView("City currently not supported.");
            }
        }
}