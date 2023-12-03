package interface_adapter.restaurant;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RestaurantViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Restaurant Info.";
    public static final String RETURN_LABEL = "Return";
    public static final String WRITE_REVIEW_LABEL = "Write a Review";
    public static final String ADD_TO_FAVOURITE_LABEL = "Add to Favorites";
    private RestaurantState state = new RestaurantState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public RestaurantViewModel(){
        super("Restaurant");
    }

    public void setState(RestaurantState state) {
        this.state = state;
    }

    public RestaurantState getState() {
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
