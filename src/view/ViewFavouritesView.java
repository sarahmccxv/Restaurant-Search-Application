package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import entity.Restaurant;
import interface_adapter.login.LoginController;
import interface_adapter.remove_favourite.RemoveFavouriteController;
import interface_adapter.remove_favourite.RemoveFavouriteViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantState;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_favourites.ViewFavouritesState;

public class ViewFavouritesView extends JPanel implements ActionListener, PropertyChangeListener{
    public static final String viewName = "view favourites";
    final JButton returnBack;
    final JPanel favourites;
    private ViewFavouritesViewModel viewFavouritesViewModel;
    private LoginController loginController;
    private RestaurantController restaurantController;
    RemoveFavouriteController removeFavouriteController;
    RemoveFavouriteViewModel removeFavouriteViewModel;

    public ViewFavouritesView(ViewFavouritesViewModel viewFavouritesViewModel,
                              LoginController loginController,
                              RestaurantController restaurantController,
                              RemoveFavouriteController removeFavouriteController,
                              RemoveFavouriteViewModel removeFavouriteViewModel){
        this.viewFavouritesViewModel = viewFavouritesViewModel;
        this.loginController = loginController;
        this.restaurantController = restaurantController;
        this.removeFavouriteController = removeFavouriteController;
        this.removeFavouriteViewModel = removeFavouriteViewModel;

        viewFavouritesViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(ViewFavouritesViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        favourites = new JPanel();
        favourites.setLayout(new BoxLayout(favourites, BoxLayout.Y_AXIS));
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(favourites);

        JPanel buttons = new JPanel();
        returnBack = new JButton(ViewFavouritesViewModel.RETURN_LABEL);
        buttons.add(returnBack);
        returnBack.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(centerPanel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewFavouritesState state = (ViewFavouritesState) evt.getNewValue();
        favourites.removeAll();
        favourites.revalidate();
        favourites.repaint();
        if (!state.getSuccess()){
            favourites.add(new JLabel(state.getNoFavouritesMessage()));
        } else {
            for (Restaurant favourite : state.getFavouritesList()) {
                JPanel restaurant = new JPanel();

                String restaruant_name = "<html><b>" + favourite.getRestaurantName() + "</b><html>";
                JButton view_button = new JButton("View");
                view_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String restaurantID = favourite.getRestaurantID();
                        String userID = state.getUserID();
                        String username = state.getUsername();
                        String password = state.getPassword();
                        restaurantController.execute(userID, username, password, restaurantID,
                                "view favourites");
                    }
                });

                JButton remove_button = new JButton("Remove");
                remove_button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String username = state.getUsername();
                        removeFavouriteController.execute(username, favourite);
                        String message = removeFavouriteViewModel.getState().getMessage();
                        JOptionPane.showMessageDialog(null, message);
                    }
                });

                restaurant.add(new JLabel(restaruant_name));
                restaurant.add(view_button);
                restaurant.add(remove_button);

                favourites.add(restaurant);

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ViewFavouritesState state = viewFavouritesViewModel.getState();
        loginController.execute(state.getUsername(), state.getPassword());
    }
}