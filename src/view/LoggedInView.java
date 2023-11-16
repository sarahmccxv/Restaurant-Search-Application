package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_favourites.ViewFavouritesState;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final ViewFavouritesViewModel viewFavouritesViewModel;
    private final ViewFavouritesController viewFavouritesController;

    private final ViewRestaurantController viewRestaurantController;

    private final ViewFavouritesViewModel viewRestaurantViewModel;

    JLabel username;

    final JButton logOut;
    final JButton viewFavourites;
    final JButton viewRestaurant;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewFavouritesViewModel viewFavouritesViewModel,
                        ViewFavouritesController viewFavouritesController) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.viewFavouritesViewModel = viewFavouritesViewModel;
        this.viewFavouritesController = viewFavouritesController;

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);
        viewFavourites = new JButton(loggedInViewModel.VIEW_FAVOURITES_BUTTON_LABEL);
        buttons.add(viewFavourites);
        viewRestaurant = new JButton(loggedInViewModel.VIEW_RESTAURANT_BUTTON_LABEL);

        logOut.addActionListener(this);
        viewFavourites.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(viewFavourites)) {
                            String username = loggedInViewModel.getState().getUsername();
                            viewFavouritesController.execute(username);
                        }
                    }
                }
        );

        viewRestaurant.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(viewRestaurant)) {
                            // TODO: Change these
                            String username = loggedInViewModel.getState().getUsername();
                            viewRestaurantController.
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}