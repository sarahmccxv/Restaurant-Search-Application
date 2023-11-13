package use_case.register;

public class RegisterInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;

    private String location;

    public RegisterInputData(String username, String password, String repeatPassword,
                             String location) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.location = location;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getLocation() { return location; }
}
