package interface_adapter.add_to_favourites;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddToFavouritesViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Notice";
    private AddToFavouritesState state = new AddToFavouritesState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddToFavouritesViewModel() {
        super("Add to Favourites View");
    }

    public void setState(AddToFavouritesState state) {
        this.state = state;
    }

    public AddToFavouritesState getState() {
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
