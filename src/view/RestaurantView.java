package view;

import entity.Restaurant;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantState;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RestaurantView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Restaurant";
    final JButton returnBack;
    private JButton addToFavourite;
    private Restaurant restaurant;
    private RestaurantViewModel restaurantViewModel;
    private RestaurantController restaurantController;
    private ViewRestaurantController viewRestaurantController;
    // TODO: Implement Reviews later
    //private WriteReviewController writeReviewController;

    public RestaurantView(RestaurantViewModel restaurantViewModel,
                          RestaurantController restaurantController,
                          // TODO: Add review controller later
                          ViewRestaurantController viewRestaurantController){
        this.restaurantViewModel = restaurantViewModel;
        this.restaurantController = restaurantController;
        this.viewRestaurantController = viewRestaurantController;

        restaurantViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(RestaurantViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        returnBack = new JButton(RestaurantViewModel.RETURN_LABEL);
        addToFavourite = new JButton(RestaurantViewModel.ADD_TO_FAVOURITE_LABEL);

        buttons.add(returnBack);
        returnBack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(returnBack)) {
                            //System.out.println("Return button clicked");
                            RestaurantState state = restaurantViewModel.getState();
                            //System.out.println("User id is " + state.getUserID() + " password is " + state.getPassword());
                            viewRestaurantController.execute(state.getUserID(), state.getUsername(), state.getPassword());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println("This is property change at Restaurant View.");
        this.removeAll();
        JLabel title = new JLabel(RestaurantViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        RestaurantState state = (RestaurantState) evt.getNewValue();
        //System.out.println("I've received the update state with Restaurant ID: " + state.getRestaurant().getRestaurantID());
        this.restaurant = state.getRestaurant();

        JPanel info = new JPanel();

        JLabel restaurantName = new JLabel(
                "Restaurant Name: " + restaurant.getRestaurantName());
        JLabel restaurantAddress = new JLabel(
                "Address: " + restaurant.getAddress());
        JLabel restaurantPhoneNumber = new JLabel(
                "Phone number: " + restaurant.getPhoneNumber());
        JLabel restaurantCategories = new JLabel(
                "Categories: " + restaurant.getCategories().toString());
        // TODO: Implement Reviews
        // JLabel reviews = new JLabel(restaurant.getReviews())

        info.add(restaurantName);
        info.add(restaurantAddress);
        info.add(restaurantPhoneNumber);
        info.add(restaurantCategories);

        JPanel buttons = new JPanel();

        buttons.add(addToFavourite);
        addToFavourite.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(addToFavourite)) {
                            System.out.println("Restaurant " + restaurant.getRestaurantName() + " added to Favourite");
                            // TODO: Implement addToFavorite
                        }
                    }
                }
        );

        buttons.add(returnBack);

        this.add(title);
        this.add(info);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        //System.out.println("Return button clicked");
        RestaurantState state = restaurantViewModel.getState();
        //System.out.println("Navigating back to ViewRestaurantView. Current userID is : " + restaurantViewModel.getState().getUserID());
        viewRestaurantController.execute(state.getUserID(), state.getUsername(), state.getPassword());
    }
}
