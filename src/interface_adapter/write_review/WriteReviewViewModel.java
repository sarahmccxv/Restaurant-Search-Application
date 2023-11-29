package interface_adapter.write_review;

import interface_adapter.ViewModel;
import interface_adapter.add_review.AddReviewState;
import interface_adapter.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WriteReviewViewModel extends ViewModel {
    public final String TITLE_LABEL = "Write Review View";

    public static final String SAVE_BUTTON_LABEL = "Save";
    public static final String RETURN_BUTTON_LABEL = "Return";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private WriteReviewState state = new WriteReviewState();

    public WriteReviewViewModel() {
        super("Write Review");
    }

    public void setState(WriteReviewState state) {
        this.state = state;
    }

    public WriteReviewState getState() {return state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
