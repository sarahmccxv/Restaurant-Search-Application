package interface_adapter.sort_and_filter;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SortAndFilterViewModel extends ViewModel{
    public static final String TITLE_LABEL = "Sort and filter";
    public static final String RETURN_BUTTON_LABEL = "Return";
    public static final String APPLY_BUTTON_LABEL = "Apply";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    private SortAndFilterState state = new SortAndFilterState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SortAndFilterViewModel() {
        super("sort and filter restaurant");
    }

    public void setState(SortAndFilterState state) {
        this.state = state;
    }

    public SortAndFilterState getState() {
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
