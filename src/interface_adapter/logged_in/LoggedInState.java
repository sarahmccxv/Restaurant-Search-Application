package interface_adapter.logged_in;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInState {
    private String username = "";
    private String userID = "";
    private String password = "";
    private String location = "";
    private String imagePath = "";
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
        userID = copy.userID;
        password = copy.password;
        location = copy.location;
        imagePath = copy.imagePath;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public String getUserID() {return userID;}
    public String getPassword() {
        return password;
    }
    public String getLocation() { return location; }
    public String getImagePath() {
        return "user-fill.png";
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        propertyChangeSupport.firePropertyChange("imagePath", null, imagePath);
    }
    public void setUsername(String newUsername) {
        this.username = newUsername;
    }
    public void setUserID(String newUserID) {
        this.userID = newUserID;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setLocation(String newLocation) { this.location = newLocation; }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }
}
