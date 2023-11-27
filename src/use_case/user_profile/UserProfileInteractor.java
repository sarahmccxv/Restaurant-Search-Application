package use_case.user_profile;

import entity.User;
import entity.UserFactory;
import interface_adapter.user_profile.UserProfilePresenter;

public class UserProfileInteractor implements UserProfileInputBoundary{
    final UserProfileOutputBoundary userProfilePresenter;
    final UserProfileDataAccessInterface fileUserDataAccessObject;
    final UserFactory userFactory;

    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter,
                                 UserProfileDataAccessInterface fileUserDataAccessObject,
                                 UserFactory userFactory) {
        this.userProfilePresenter = userProfilePresenter;
        this.fileUserDataAccessObject = fileUserDataAccessObject;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(UserProfileInputData userProfileInputData) {
        Integer userID = userProfileInputData.getUserID();
        String username = userProfileInputData.getUsername();
        String password = userProfileInputData.getPassword();
        String location = userProfileInputData.getLocation();
        fileUserDataAccessObject.update();
        User oldUser = fileUserDataAccessObject.get(userID);
        User newUser = userFactory.create(userID, username, password, location, oldUser.getCreationTime());
        fileUserDataAccessObject.updateUserInfo(newUser);
//        System.out.println("UserProfileInteractor" + newUser.getLocation());

        UserProfileOutputData userProfileOutputData = new UserProfileOutputData(userID,
                username, password, location);
        userProfilePresenter.prepareSuccessView(userProfileOutputData);
    }
}
