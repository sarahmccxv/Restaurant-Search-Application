package view;

import entity.FavouritesList;
import entity.Restaurant;
import interface_adapter.login.LoginController;
import interface_adapter.remove_favourite.RemoveFavouriteController;
import interface_adapter.remove_favourite.RemoveFavouriteViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.view_favourites.ViewFavouritesState;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ViewFavouritesViewTest {

    private ViewFavouritesView view;
    private ViewFavouritesViewModel viewModel;
    private LoginController loginController;
    private RestaurantController restaurantController;
    private RemoveFavouriteController removeFavouriteController;
    private RemoveFavouriteViewModel removeFavouriteViewModel;

    @BeforeEach
    void setUp() {
        viewModel = mock(ViewFavouritesViewModel.class);
        loginController = mock(LoginController.class);
        restaurantController = mock(RestaurantController.class);
        removeFavouriteController = mock(RemoveFavouriteController.class);
        removeFavouriteViewModel = mock(RemoveFavouriteViewModel.class);

        view = new ViewFavouritesView(viewModel, loginController, restaurantController,
                removeFavouriteController, removeFavouriteViewModel);
    }

    @Test
    void testPropertyChange_NoFavourites() {
        // Mock property change event
        PropertyChangeEvent event = mock(PropertyChangeEvent.class);
        FavouritesList favouritesList = new FavouritesList();

        // Simulate no favourites in the state
        ViewFavouritesState state = new ViewFavouritesState();
        state.setFavouritesList(favouritesList);
        state.setSuccess(false);
        state.setNoFavouritesMessage("No favourites found.");

        // Set up the mocked event
        when(event.getNewValue()).thenReturn(state);

        // Trigger property change method
        view.propertyChange(event);

        // Verify that the view correctly updates when there are no favourites
        assertEquals(1, view.favourites.getComponentCount());
        assertEquals("No favourites found.", ((JLabel) view.favourites.getComponent(0)).getText());
    }

    @Test
    void testPropertyChange_WithFavourites() {
        // Mock property change event
        PropertyChangeEvent event = mock(PropertyChangeEvent.class);
        FavouritesList favouritesList = new FavouritesList();
        Restaurant restaurant = new Restaurant("1", "Restaurant 1", "Address 1", "123",  new ArrayList<>(), "https://example.com");
        favouritesList.add(restaurant);
        favouritesList.add(restaurant);


        // Simulate favourites in the state
        ViewFavouritesState state = new ViewFavouritesState();
        state.setFavouritesList(favouritesList);
        state.setSuccess(true);

        // Set up the mocked event
        when(event.getNewValue()).thenReturn(state);

        // Trigger property change method
        view.propertyChange(event);

        // Verify that the view correctly updates when there are favourites
        assertEquals(2, view.favourites.getComponentCount());
        // Add more specific assertions based on your actual view logic
    }

    // Add more tests for other scenarios and functionality as needed
}
