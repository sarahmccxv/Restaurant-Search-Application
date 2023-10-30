package use_case.register;

public class RegisterInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;

    public RegisterInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
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
}
