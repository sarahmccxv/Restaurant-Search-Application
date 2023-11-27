package interface_adapter.user_profile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserProfileViewModel extends ViewModel {
    public String TITLE_LABEL = "Profile View";
    public String USERNAME_LABEL = "Username";
    public String USERID_LABEL = "User ID";
    public String LOCATION_LABEL = "Location";
    public String PASSWORD_LABEL = "Password";
    public String EDIT_BUTTON_LABEL = "Edit";
    public String SAVE_BUTTON_LABEL = "Save";
    public String CANCEL_BUTTON_LABEL = "Cancel";
    public String RETURN_BUTTON_LABEL = "Back";

    private UserProfileState lastState = new UserProfileState();
    private UserProfileState state = new UserProfileState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public UserProfileViewModel() {
        super("user profile");
    }

    public void setState(UserProfileState state) {
        this.lastState = this.state;
        this.state = state;
    }

    public void setLastState() {
        this.state = this.lastState;
//        this.lastState = new UserProfileState();
    }

    public UserProfileState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}