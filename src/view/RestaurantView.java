package view;

import entity.Restaurant;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_favourites.AddToFavouritesViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantState;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.add_to_favourites.AddToFavouritesController;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;

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
    final JPanel info;
    final JPanel buttons;
    private Restaurant restaurant;
    private RestaurantViewModel restaurantViewModel;
    private RestaurantController restaurantController;
    private ViewRestaurantController viewRestaurantController;
    // TODO: Implement Reviews later
    //private WriteReviewController writeReviewController;
    private AddToFavouritesController addToFavouritesController;
    private AddToFavouritesViewModel addToFavouritesViewModel;
    private ViewFavouritesController viewFavouritesController;
    private ViewManagerModel viewManagerModel;


    public RestaurantView(RestaurantViewModel restaurantViewModel,
                          RestaurantController restaurantController,
                          // TODO: Add review controller later
                          ViewRestaurantController viewRestaurantController,
                          AddToFavouritesController addToFavouritesController,
                          AddToFavouritesViewModel addToFavouritesViewModel,
                          ViewFavouritesController viewFavouritesController,
                          ViewManagerModel viewManagerModel){
        this.restaurantViewModel = restaurantViewModel;
        this.restaurantController = restaurantController;
        this.viewRestaurantController = viewRestaurantController;
        this.addToFavouritesController = addToFavouritesController;
        this.addToFavouritesViewModel = addToFavouritesViewModel;
        this.viewFavouritesController = viewFavouritesController;
        this.viewManagerModel = viewManagerModel;

        restaurantViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(RestaurantViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(info);

        buttons = new JPanel();
        returnBack = new JButton(RestaurantViewModel.RETURN_LABEL);
        buttons.add(returnBack);
        returnBack.addActionListener(this);


        addToFavourite = new JButton(RestaurantViewModel.ADD_TO_FAVOURITE_LABEL);
        addToFavourite.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(addToFavourite)) {
                            RestaurantState state = restaurantViewModel.getState();
                            Restaurant restaurant = state.getRestaurant();
                            String username = state.getUsername();
                            addToFavouritesController.execute(username, restaurant);
                            String message = addToFavouritesViewModel.getState().getMessage();
                            JOptionPane.showMessageDialog(null, message);
                            }
                    }
                });
        buttons.add(addToFavourite);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(centerPanel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RestaurantState state = (RestaurantState) evt.getNewValue();
        this.restaurant = state.getRestaurant();
        info.removeAll();
        info.revalidate();
        info.repaint();
        JLabel restaurantAddress = new JLabel(
                "Address: " + restaurant.getAddress());
        JLabel restaurantPhoneNumber = new JLabel(
                "Phone number: " + restaurant.getPhoneNumber());
        JLabel restaurantCategories = new JLabel(
                "Categories: " + restaurant.getCategories().toString());
        // TODO: Implement Reviews
        // JLabel reviews = new JLabel(restaurant.getReviews())
        info.add(new JLabel("Restaurant Name: " + restaurant.getRestaurantName()));
        info.add(restaurantAddress);
        info.add(restaurantPhoneNumber);
        info.add(restaurantCategories);

        if (state.getPreviousView().equals("view restaurants")) {
            addToFavourite.setVisible(true);
        } else if (state.getPreviousView().equals("view favourites")) {
            addToFavourite.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        RestaurantState state = restaurantViewModel.getState();
        if (state.getPreviousView().equals("view restaurants")) {
            viewManagerModel.setActiveView("view restaurant");
            viewManagerModel.firePropertyChanged();
//            viewRestaurantController.execute(state.getUserID(), state.getUsername(), state.getPassword(), "Beijing");
        } else if (state.getPreviousView().equals("view favourites")) {
            viewFavouritesController.execute(state.getUsername());
        }else {
            System.out.println("ohno");
        }
    }
}



