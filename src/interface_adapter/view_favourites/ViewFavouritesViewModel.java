package interface_adapter.view_favourites;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewFavouritesViewModel extends ViewModel{

    public static final String TITLE_LABEL = "View Favourites";
    public static final String VIEW_FAVOURITES_BUTTON_LABEL = "View Favourites";
    private ViewFavouritesState state = new ViewFavouritesState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewFavouritesViewModel() {
        super("view favourites view");
    }

    public void setState(ViewFavouritesState state) {
        this.state = state;
    }

    public ViewFavouritesState getState() {
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
