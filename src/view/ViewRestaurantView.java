package view;

import api.Search.SearchCriteria;
import entity.Restaurant;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.search_restaurants.SearchRestaurantController;
import interface_adapter.sort_and_filter.SortAndFilterController;
import interface_adapter.sort_and_filter.SortAndFilterState;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantState;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ViewRestaurantView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "view restaurant";
    final JTextField searchInputField = new JTextField(15);
    final JButton returnBack;
    final JButton sortAndFilter;
    final JButton search;
    final JPanel restaurants;
    private final SearchRestaurantController searchRestaurantController;
    private final SortAndFilterViewModel sortAndFilterViewModel;
    private final SortAndFilterController sortAndFilterController;
    private final ViewRestaurantViewModel viewRestaurantViewModel;
    private final LoginController loginController;
    private final RestaurantController restaurantController;

    public ViewRestaurantView(ViewRestaurantViewModel viewRestaurantViewModel,
                              ViewRestaurantController viewRestaurantController,
                              LoginController loginController,
                              RestaurantController restaurantController,
                              SortAndFilterController sortAndFilterController,
                              SortAndFilterViewModel sortAndFilterViewModel,
                              SearchRestaurantController searchRestaurantController){
        this.viewRestaurantViewModel = viewRestaurantViewModel;
        this.loginController = loginController;
        this.restaurantController = restaurantController;
        this.sortAndFilterController = sortAndFilterController;
        this.sortAndFilterViewModel = sortAndFilterViewModel;
        this.searchRestaurantController = searchRestaurantController;

        viewRestaurantViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(ViewRestaurantViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel message = new JLabel(ViewRestaurantViewModel.MESSAGE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        restaurants = new JPanel();
        restaurants.setLayout(new BoxLayout(restaurants, BoxLayout.Y_AXIS));
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(restaurants);

        JPanel searchPanel = new JPanel(new GridBagLayout());
        LabelTextPanel search_restaurant = new LabelTextPanel(new JLabel(" "), searchInputField);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  // Set the X coordinate for the first component
        gbc.gridy = 0;  // Set the Y coordinate for the first component
        gbc.insets = new Insets(0, 0, 0, 10);  // Optional: Add some spacing between components

        searchPanel.add(search_restaurant, gbc);

        gbc.gridx = 1;  // Set the X coordinate for the second component
        gbc.insets = new Insets(0, 0, 0, 0);  // Reset insets if needed
        search = new JButton(ViewRestaurantViewModel.SEARCH_LABEL);
        searchPanel.add(search, gbc);


        searchInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ViewRestaurantState currentState = viewRestaurantViewModel.getState();
                        String text = searchInputField.getText() + e.getKeyChar();
                        currentState.setRestaurantName(text);
                        viewRestaurantViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });


        JPanel sort_button = new JPanel();
        sortAndFilter = new JButton(ViewRestaurantViewModel.SORTANDFILTER_LABEL);
        sort_button.add(sortAndFilter);

        JPanel buttons = new JPanel();
        returnBack = new JButton(ViewRestaurantViewModel.RETURN_LABEL);
        buttons.add(returnBack);
        returnBack.addActionListener(this);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(search_restaurant);
        this.add(searchPanel);
        this.add(sort_button);
        this.add(message);
        this.add(centerPanel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewRestaurantState state = (ViewRestaurantState) evt.getNewValue();
        String location = state.getLocation();
        restaurants.removeAll();
        restaurants.revalidate();
        restaurants.repaint();

        for (Restaurant restaurant : state.getRestaurants()) {
            JPanel res = new JPanel();
            String buttonText = restaurant.getRestaurantName() + " - " + restaurant.getAddress();
            JButton button = new JButton(buttonText);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    //System.out.println("Go to restaurant " + restaurant.getRestaurantName());
                    String restaurantID = restaurant.getRestaurantID();
                    String userID = state.getUserID();
                    String username = state.getUsername();
                    String password = state.getPassword();
                    restaurantController.execute(userID, username, password, restaurantID, "view restaurants");
                }
            });
            res.add(button);
            restaurants.add(res);
        }

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Restaurant> searchResult = state.getRestaurants();
                for (Restaurant restaurant : searchResult) {
                    System.out.println("in search state");
                    String restaurantID = restaurant.getRestaurantID();
                    String userID = state.getUserID();
                    String username = state.getUsername();
                    String password = state.getPassword();
                    String previousView = state.getPreviousView();
                    String buttonText = restaurant.getRestaurantName() + " - " + restaurant.getAddress();
                    restaurantController.execute(userID, username, password, restaurantID, previousView);
                    searchRestaurantController.execute(state.getLocation(), state.getRestaurantName());
                }
        }
        });

        sortAndFilter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(sortAndFilter)) {
                            SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
                            sortAndFilterState.getCriteria().setLocation(location);
                            System.out.println("in sort state");
                            SearchCriteria criteria = sortAndFilterState.getCriteria();
                            sortAndFilterController.execute(criteria);
                        }
                    }
                }
        );

        }

    @Override
    public void actionPerformed(ActionEvent evt) {
            System.out.println("Return button clicked");
            ViewRestaurantState state = viewRestaurantViewModel.getState();
            loginController.execute(state.getUsername(), state.getPassword());
    }
}