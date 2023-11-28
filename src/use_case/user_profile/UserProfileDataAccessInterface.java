package use_case.user_profile;

import entity.User;

public interface UserProfileDataAccessInterface {
    User getByUserID(String userID);
    void updateUserInfo(User user);
    void update();
}
