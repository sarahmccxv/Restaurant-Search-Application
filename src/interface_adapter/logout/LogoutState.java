package interface_adapter.logout;

public class LogoutState {
    private String message = "";

    public LogoutState(LogoutState copy){
        this.message = copy.message;
    }

    public LogoutState(){};

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
