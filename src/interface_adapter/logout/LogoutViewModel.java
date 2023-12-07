package interface_adapter.logout;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LogoutViewModel extends ViewModel {
    public String message;
    private LogoutState state = new LogoutState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LogoutViewModel(){
        super("log out");
    }

    public void setState(LogoutState state){
        this.state = state;
    }

    public LogoutState getState() {
        return state;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
