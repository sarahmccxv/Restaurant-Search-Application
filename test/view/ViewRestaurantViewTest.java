package view;

import api.Search.SearchCriteria;
import app.Main;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ViewRestaurantViewTest {
    static String message = "";
    static boolean popUpDiscovered = false;

    private ViewRestaurantViewModel viewRestaurantViewModel;
    private ViewRestaurantController viewRestaurantController;
    private LoginController loginController;
    private RestaurantController restaurantController;
    private SortAndFilterController sortAndFilterController;
    private SortAndFilterViewModel sortAndFilterViewModel;
    private SearchRestaurantController searchRestaurantController;

    private ViewRestaurantView viewRestaurantView;

    @BeforeEach
    public void setUp() {
        viewRestaurantViewModel = mock(ViewRestaurantViewModel.class);
        viewRestaurantController = mock(ViewRestaurantController.class);
        loginController = mock(LoginController.class);
        restaurantController = mock(RestaurantController.class);
        sortAndFilterController = mock(SortAndFilterController.class);
        sortAndFilterViewModel = mock(SortAndFilterViewModel.class);
        searchRestaurantController = mock(SearchRestaurantController.class);

        viewRestaurantView = new ViewRestaurantView(viewRestaurantViewModel, viewRestaurantController,
                loginController, restaurantController, sortAndFilterController, sortAndFilterViewModel,
                searchRestaurantController);
    }

    @Test
    public void testPropertyChange_Restaurants() {
        // Mocked event
        PropertyChangeEvent event = mock(PropertyChangeEvent.class);

        // Mock state with restaurants
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("1", "Restaurant 1", "Address 1", "123",  new ArrayList<>(), "https://example.com");
        restaurants.add(restaurant);
        restaurants.add(restaurant);

        ViewRestaurantState state = new ViewRestaurantState();
        state.setRestaurants(restaurants);

        // Mock SortAndFilterState
        SortAndFilterState sortAndFilterState = mock(SortAndFilterState.class);

        // Mock SearchCriteria
        SearchCriteria searchCriteria = mock(SearchCriteria.class);
        when(sortAndFilterState.getCriteria()).thenReturn(searchCriteria);

        // Mock SortAndFilterViewModel
        when(sortAndFilterViewModel.getState()).thenReturn(sortAndFilterState);

        // Simulate property change event with restaurants
        when(event.getPropertyName()).thenReturn("restaurants");
        when(event.getNewValue()).thenReturn(state);

        // Invoke property change method
        viewRestaurantView.propertyChange(event);
    }



//    private JLabel getTitle() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JLabel title = (JLabel) sv.getComponent(0);
//
//        return title;
//    }
//    private JTextField getSearchTextField() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        LabelTextPanel searchRestaurant = (LabelTextPanel) sv.getComponent(1);
//
//        JTextField searchText = (JTextField) searchRestaurant.getComponent(1);
//
//        return searchText;
//    }

    private JButton getSortButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();

        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);

        Component root = app.getComponent(0);
        JButton sortButton = (JButton) ((JRootPane) root).getContentPane().getComponent(0);
//        ViewRestaurantView viewRestaurantView = (ViewRestaurantView) mainPanel.getComponent(0);
//
//        JPanel searchButtonPanel = (JPanel) viewRestaurantView.getComponent(2);
//        JButton searchButton = (JButton) searchButtonPanel.getComponent(0);

        return sortButton;

//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JButton jp2 = (JButton) jp.getComponent(0);

//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JPanel searchButtonPanel = (JPanel) sv.getComponent(2);
//
//        JButton searchButton = (JButton) searchButtonPanel.getComponent(0);

    }

//    private JButton getSortButton() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JPanel sortButtonPanel = (JPanel) sv.getComponent(3);
//
//        JButton sortButton = (JButton) sortButtonPanel.getComponent(0);
//
//        return sortButton;
//    }

//    private JLabel getMessage() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JLabel message = (JLabel) sv.getComponent(4);
//
//        return message;
//    }
//    private JButton getReturnButton() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JPanel buttons = (JPanel) sv.getComponent(5);
//
//        JButton returnButton = (JButton) buttons.getComponent(0);
//
//        return returnButton;
//    }

//    @Test
//    public void titlePresent(){
//        JLabel title = getTitle();
//        assertEquals("View Restaurants", title.getText());
//        assert(popUpDiscovered);
//        assert(message.equals("Your note has been saved."));
//    }
//
//    @Test
//    public void searchTextFieldPresent(){
//        JTextField searchTextField = getSearchTextField();
//        searchTextField.setText("china");
//        assertEquals("china",searchTextField.getText());
//        assertFalse(popUpDiscovered);
//        assert(message.equals("Your note has been saved."));
//    }

    @Test
    public void sortButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getSortButton();
        System.out.println(button.getText());
        assert(button.getText().equals("Sort and filter"));
    }

//    @Test
//    public void sortButtonPresent() {
//        JButton button = getSortButton();
//        assert(button.getText().equals("Sort and filter"));
//    }
//
//    @Test
//    public void messagePresent() {
//        JLabel message = getMessage();
//        assertEquals("Matched Results: ", message.getText());
//        assert(popUpDiscovered);
//        assert(message.equals("Your note has been saved."));
//    }
//
//    @Test
//    public void returnButtonPresent() {
//        JButton button = getReturnButton();
//        assert(button.getText().equals("Return"));
//    }

}