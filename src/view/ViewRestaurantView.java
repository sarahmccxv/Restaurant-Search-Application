package view;

        import api.Search.SearchCriteria;
        import entity.Restaurant;
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
    private SearchRestaurantController searchRestaurantController;
    private SortAndFilterViewModel sortAndFilterViewModel;
    private SortAndFilterController sortAndFilterController;
    private ViewRestaurantViewModel viewRestaurantViewModel;
    private ViewRestaurantController viewRestaurantController;
    private LoginController loginController;
    private RestaurantController restaurantController;

    public ViewRestaurantView(ViewRestaurantViewModel viewRestaurantViewModel,
                              ViewRestaurantController viewRestaurantController,
                              LoginController loginController,
                              RestaurantController restaurantController,
                              SortAndFilterController sortAndFilterController,
                              SortAndFilterViewModel sortAndFilterViewModel,
                              SearchRestaurantController searchRestaurantController){
        this.viewRestaurantViewModel = viewRestaurantViewModel;
        this.viewRestaurantController = viewRestaurantController;
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
        JFrame frame = new JFrame("View restaurant buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        restaurants = new JPanel();
        restaurants.setLayout(new BoxLayout(restaurants, BoxLayout.Y_AXIS));

        LabelTextPanel search_restaurant = new LabelTextPanel(new JLabel("Search by name"), searchInputField);

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

        JPanel search_button = new JPanel();
        search = new JButton(ViewRestaurantViewModel.SEARCH_LABEL);
        search.setBackground(Color.BLUE); // for the background
        search.setForeground(Color.WHITE); // for the text
        frame.setContentPane(search_button);
        frame.pack();
        frame.setVisible(false);
        search_button.add(search);
        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            ViewRestaurantState currentState = viewRestaurantViewModel.getState();
                            searchRestaurantController.execute(currentState.getLocation(), currentState.getRestaurantName());
                        }
                    }
                }
        );

        JPanel sort_button = new JPanel();
        sortAndFilter = new JButton(ViewRestaurantViewModel.SORTANDFILTER_LABEL);
        sort_button.add(sortAndFilter);
        sortAndFilter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(sortAndFilter)) {
                            SortAndFilterState state = sortAndFilterViewModel.getState();
                            SearchCriteria criteria = state.getCriteria();
                            String previousView = state.getPreviousView();
                            sortAndFilterController.execute(criteria, previousView);
//                            System.out.println("Is executed");
                            CardLayout cardLayout = (CardLayout) getParent().getLayout();
                            cardLayout.show(getParent(), "sortAndFilterView");
                        }

                    }
                }
        );


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
        this.add(search_restaurant);
        this.add(search_button);
        this.add(sort_button);
        this.add(message);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JFrame frame = new JFrame("View restaurant buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.removeAll();
        restaurants.removeAll();
        restaurants.revalidate();
        restaurants.repaint();
        JLabel title = new JLabel(ViewRestaurantViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Set font
        Font titleFont = new Font("Arial", Font.BOLD, 18); // Change "Arial" to the desired font family
        title.setFont(titleFont);
        JLabel message = new JLabel(ViewRestaurantViewModel.MESSAGE_LABEL);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        ViewRestaurantState state = (ViewRestaurantState) evt.getNewValue();
        System.out.println("property name is " + evt.getPropertyName());
        if ("restaurants".equals(evt.getPropertyName())) {
            System.out.println("im here");
        }


        String location = state.getLocation();
        String userID = state.getUserID();
        String username = state.getUsername();
        String password = state.getPassword();

        searchInputField.setText("");

        SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
        sortAndFilterState.getCriteria().setLocation(location);
        System.out.println(sortAndFilterState.getCriteria().getLocation());
        sortAndFilterViewModel.setState(sortAndFilterState);

        for (Restaurant restaurant : state.getRestaurants()) {
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
            restaurants.add(button);
        }
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
        JButton search = new JButton(ViewRestaurantViewModel.SEARCH_LABEL);
        search.setBackground(Color.BLUE); // for the background
        search.setForeground(Color.WHITE); // for the text
        frame.setContentPane(searchPanel);
        frame.pack();
        frame.setVisible(false);
        searchPanel.add(search, gbc);
        System.out.println("gbc");
        ViewRestaurantState searchRestaurantState = (ViewRestaurantState) evt.getNewValue();
        System.out.println(searchRestaurantState.getRestaurants());

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurants.removeAll();
                restaurants.revalidate();
                restaurants.repaint();
                for (Restaurant restaurant : searchRestaurantState.getRestaurants()) {
                    String buttonText = restaurant.getRestaurantName() + " - " + restaurant.getAddress();
                    JButton button = new JButton(buttonText);
                    ViewRestaurantState currentState = viewRestaurantViewModel.getState();
                    System.out.println("in search state");
                    String restaurantID = restaurant.getRestaurantID();
                    String userID = state.getUserID();
                    String username = state.getUsername();
                    String password = state.getPassword();
                    String previousView = state.getPreviousView();
                    restaurantController.execute(userID, username, password, restaurantID, "view restaurants");
                    searchRestaurantController.execute(currentState.getLocation(), currentState.getRestaurantName());
                    restaurants.add(button);
                    System.out.println("button added");
                }
                restaurants.remove(5);
            }
        });


        JPanel sort_button = new JPanel();
        JButton sortAndFilter = new JButton(ViewRestaurantViewModel.SORTANDFILTER_LABEL);
        sort_button.add(sortAndFilter);
        sortAndFilter.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(sortAndFilter)) {
                            SortAndFilterState sortAndFilterState = sortAndFilterViewModel.getState();
                            sortAndFilterState.setLocation(location);
                            sortAndFilterState.setCategory(state.getRestaurantName());
                            sortAndFilterState.setUserID(userID);
                            sortAndFilterState.setPassword(password);
                            sortAndFilterState.setUsername(username);
                            System.out.println("in sort state");
                            SearchCriteria criteria = sortAndFilterState.getCriteria();
                            String previousView = sortAndFilterState.getPreviousView();
                            System.out.println(previousView + "is the previous view after sort in view restaurant");
                            sortAndFilterController.execute(criteria, previousView);
                        }

                        }
                }
        );


        JPanel buttons = new JPanel();
        buttons.add(returnBack);

        this.add(title);
        this.add(searchPanel);
        this.add(sort_button);
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