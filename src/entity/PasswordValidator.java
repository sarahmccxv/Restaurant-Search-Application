package entity;

public class PasswordValidator{
    public boolean isValidPassword(String password){
        return password != null && password.length() > 5;
    }
}
