package use_case.login;

public class LoginOutputData {

    private final String userID;
    private final String username;
    private boolean useCaseFailed;
    private final String password;

    public LoginOutputData(String userID, String username, String password,
                           boolean useCaseFailed) {
        this.userID = userID;
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;}

    public String getPassword(){
        return password;
    }

}
