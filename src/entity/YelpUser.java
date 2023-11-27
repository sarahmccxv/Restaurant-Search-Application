package entity;

public class YelpUser extends CommonUser implements YelpUserInterface {

    public YelpUser(String userID, String username) {
        super(userID, username);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getUserID() {
        return super.getUserID();
    }
}
