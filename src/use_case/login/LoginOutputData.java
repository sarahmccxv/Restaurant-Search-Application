package use_case.login;

public class LoginOutputData {

    private final Integer userID;
    private final String username;
    private boolean useCaseFailed;
    private final String password;
    private final String location;

    public LoginOutputData(Integer userID, String username, String password,
                           boolean useCaseFailed, String location) {
        this.userID = userID;
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.password = password;
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public Integer getUserID() {
        return userID;}

    public String getPassword(){
        return password;
    }

    public String getLocation(){ return location;}
}
