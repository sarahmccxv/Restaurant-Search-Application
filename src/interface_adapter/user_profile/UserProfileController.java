package interface_adapter.user_profile;

import use_case.user_profile.UserProfileInputData;
import use_case.user_profile.UserProfileInteractor;

public class UserProfileController {
    final UserProfileInteractor userProfileInteractor;

    public UserProfileController(UserProfileInteractor userProfileInteractor) {
        this.userProfileInteractor = userProfileInteractor;
    }

    public void execute(String userID, String username, String password, String location) {
        UserProfileInputData userProfileInputData = new UserProfileInputData(userID,
                username, password, location);
        userProfileInteractor.execute(userProfileInputData);
    }
}