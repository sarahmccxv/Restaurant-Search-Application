package entity;

public class CommonUser {
    private final String userID;
    private final String username;
    //private final ArrayList<Review> reviewsList = new ArrayList<>();

    public CommonUser(String userID, String username){
        this.userID = userID;
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
}
