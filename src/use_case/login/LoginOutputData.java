package use_case.login;

public class LoginOutputData {

    private final String userID;
    private final String username;
    private boolean useCaseFailed;
    private final String password;
    private final String location;


    public LoginOutputData(Integer userID, String username, String password,
                           String location, boolean useCaseFailed) {
        this.userID = userID;
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.password = password;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;}

    public String getPassword(){
        return password;
    }

    public String getLocation() {
        return location;
    }

}
