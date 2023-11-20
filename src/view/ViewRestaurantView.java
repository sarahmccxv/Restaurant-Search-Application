package view;


import data_access.APIRestaurantDataAccessObject;
import entity.Restaurant;
import interface_adapter.login.LoginController;
import interface_adapter.view_favourites.ViewFavouritesState;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantState;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ViewRestaurantView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "view restaurant";
    final JButton returnBack;
    final JPanel restaurants;
    private ArrayList<Restaurant> restaurantsData;
    private ViewRestaurantViewModel viewRestaurantViewModel;
    private ViewRestaurantController viewRestaurantController;
    private LoginController loginController;

    public ViewRestaurantView(ViewRestaurantViewModel viewRestaurantViewModel,
                              ViewRestaurantController viewRestaurantController,
                              LoginController loginController){
        this.viewRestaurantViewModel = viewRestaurantViewModel;
        this.viewRestaurantController = viewRestaurantController;
        this.loginController = loginController;

        viewRestaurantViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(ViewRestaurantViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.restaurantsData = viewRestaurantViewModel.getState().getRestaurants();

        restaurants = new JPanel();
        restaurants.setLayout(new BoxLayout(restaurants, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();

        for (Restaurant restaurant : restaurantsData) {
            String buttonText = restaurant.getRestaurantName() + " - " + restaurant.getAddress();
            JButton button = new JButton(buttonText);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // TODO: Navigate the user to the Restaurant Page
                    System.out.println("Go to restaurant " + restaurant.getRestaurantName());
                }
            });

            restaurants.add(button);
        }

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(restaurants);

        returnBack = new JButton(ViewRestaurantViewModel.RETURN_LABEL);
        buttons.add(returnBack);
        returnBack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(returnBack)) {
                            System.out.println("Return button clicked");
                            ViewRestaurantState state = viewRestaurantViewModel.getState();
                            System.out.println("User name is " + state.getUsername() + " password is " + state.getPassword());
                            loginController.execute(state.getUsername(), state.getPassword());
                        }
                    }
                }
                );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(centerPanel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //ViewRestaurantState state = (ViewRestaurantState) evt.getNewValue();
        //restaurants.removeAll();
        //restaurants.revalidate();
        //restaurants.repaint();
        //for (Restaurant restaurant : state.getRestaurants()) {
            //String restaurantText = restaurant.getRestaurantName();
            //String text = "<html><b>" + restaurantText.split("\n")[0] + "</b><br>" +
                   //restaurantText.split("\n")[1] + "<br>" + restaurantText.split("\n")[2] + "</html>";
            //restaurants.add(new JLabel(restaurantText));
            //}
        }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Return button clicked");
        ViewRestaurantState state = viewRestaurantViewModel.getState();
        loginController.execute(state.getUsername(), state.getPassword());
    }
}