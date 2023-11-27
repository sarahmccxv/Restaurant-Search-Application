package interface_adapter.add_review;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddReviewViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Add Review";
    private AddReviewState state = new AddReviewState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddReviewViewModel(){super("Add Review");}

    public void setState(AddReviewState addReviewState){this.state = addReviewState;}

    public AddReviewState getState() {return state;}
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
