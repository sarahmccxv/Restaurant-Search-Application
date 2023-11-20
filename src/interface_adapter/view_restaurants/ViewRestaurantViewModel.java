package interface_adapter.view_restaurants;

import interface_adapter.ViewModel;
import interface_adapter.view_favourites.ViewFavouritesState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewRestaurantViewModel extends ViewModel {
    public static final String TITLE_LABEL = "View Restaurants";
    public static final String MESSAGE_LABEL = "Matched Results: ";
    public static final String RETURN_LABEL = "Return";
    private ViewRestaurantState state = new ViewRestaurantState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewRestaurantViewModel(){
        super("view restaurant");
    }

    public void setState(ViewRestaurantState state) {
        this.state = state;
    }

    public ViewRestaurantState getState() {
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
