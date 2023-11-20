package use_case.login;

public class LoginOutputData {

    private final Integer userID;
    private final String username;
    private boolean useCaseFailed;
    private final String password;

    public LoginOutputData(Integer userID, String username, String password,
                           boolean useCaseFailed) {
        this.userID = userID;
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Integer getUserID() {
        return userID;}

    public String getPassword(){
        return password;
    }

}
