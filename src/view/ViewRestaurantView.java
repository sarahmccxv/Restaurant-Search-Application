package view;

import entity.Restaurant;
import interface_adapter.login.LoginController;
import interface_adapter.restaurant.RestaurantController;
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
    private ViewRestaurantViewModel viewRestaurantViewModel;
    private ViewRestaurantController viewRestaurantController;
    private LoginController loginController;
    private RestaurantController restaurantController;

    public ViewRestaurantView(ViewRestaurantViewModel viewRestaurantViewModel,
                              ViewRestaurantController viewRestaurantController,
                              LoginController loginController,
                              RestaurantController restaurantController){
        this.viewRestaurantViewModel = viewRestaurantViewModel;
        this.viewRestaurantController = viewRestaurantController;
        this.loginController = loginController;
        this.restaurantController = restaurantController;

        viewRestaurantViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(ViewRestaurantViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel message = new JLabel(ViewRestaurantViewModel.MESSAGE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        restaurants = new JPanel();
        restaurants.setLayout(new BoxLayout(restaurants, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();

        returnBack = new JButton(ViewRestaurantViewModel.RETURN_LABEL);
        buttons.add(returnBack);
        returnBack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(returnBack)) {
                            //System.out.println("Return button clicked");
                            ViewRestaurantState state = viewRestaurantViewModel.getState();
                            //System.out.println("User name is " + state.getUsername() + " password is " + state.getPassword());
                            loginController.execute(state.getUsername(), state.getPassword());
                        }
                    }
                }
                );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(message);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.removeAll();
        restaurants.removeAll();
        JLabel title = new JLabel(ViewRestaurantViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel message = new JLabel(ViewRestaurantViewModel.MESSAGE_LABEL);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        ViewRestaurantState state = (ViewRestaurantState) evt.getNewValue();
        System.out.println("This is propertyChange at ViewRestaurantView. I've updated state with userID: " + state.getUserID());
        for (Restaurant restaurant:state.getRestaurants()) {
            String buttonText = restaurant.getRestaurantName() + " - " + restaurant.getAddress();
            JButton button = new JButton(buttonText);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // TODO: Navigate the user to the Restaurant Page
                    System.out.println("Go to restaurant " + restaurant.getRestaurantName());
                    String restaurantID = restaurant.getRestaurantID();
                    Integer userID = state.getUserID();
                    String username = state.getUsername();
                    String password = state.getPassword();
                    restaurantController.execute(userID, username, password, restaurantID);
                }
            });
            restaurants.add(button);
        }
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(restaurants);

        JPanel buttons = new JPanel();
        buttons.add(returnBack);

        this.add(title);
        this.add(message);
        this.add(centerPanel);
        this.add(buttons);
        }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Return button clicked");
        ViewRestaurantState state = viewRestaurantViewModel.getState();
        loginController.execute(state.getUsername(), state.getPassword());
    }
}