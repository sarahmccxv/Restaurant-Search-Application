package use_case.user_profile;

import entity.User;

public interface UserProfileDataAccessInterface {
    User get(Integer userID);
    void updateUserInfo(User user);
    void update();
}
