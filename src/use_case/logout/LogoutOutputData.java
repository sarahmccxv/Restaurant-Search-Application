package use_case.logout;

public class LogoutOutputData {
    private String username;

    public LogoutOutputData(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
