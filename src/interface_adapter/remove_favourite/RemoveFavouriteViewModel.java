package interface_adapter.remove_favourite;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RemoveFavouriteViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Notice";
    private RemoveFavouriteState state = new RemoveFavouriteState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public RemoveFavouriteViewModel() {
        super("Removed Favourite View");
    }

    public void setState(RemoveFavouriteState state) {
        this.state = state;
    }

    public RemoveFavouriteState getState() {
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

