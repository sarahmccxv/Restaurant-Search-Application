package view;

import data_access.APIRestaurantDataAccessObject;
import data_access.FileFavouritesDataAccessObject;
import entity.Restaurant;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_review.AddReviewController;
import interface_adapter.add_review.AddReviewState;
import interface_adapter.add_review.AddReviewViewModel;
import interface_adapter.add_to_favourites.AddToFavouritesController;
import interface_adapter.add_to_favourites.AddToFavouritesState;
import interface_adapter.add_to_favourites.AddToFavouritesViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.restaurant.RestaurantState;
import interface_adapter.restaurant.RestaurantViewModel;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_restaurants.ViewRestaurantController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_to_favourites.AddToFavouritesDataAccessInterface;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class RestaurantViewTest {
    private RestaurantView restaurantView;
    private ViewManagerModel viewManagerModel;
    private RestaurantController restaurantController;
    private RestaurantViewModel restaurantViewModel;
    private AddReviewController addReviewController;
    private AddToFavouritesController addToFavouritesController;
    private AddToFavouritesViewModel addToFavouritesViewModel;
    private AddReviewViewModel addReviewViewModel;
    private ViewFavouritesController viewFavouritesController;
    private ViewRestaurantController viewRestaurantController;
    private ViewRestaurantDataAccessInterface restaurantDataAccessObject;
    private AddToFavouritesDataAccessInterface favouriteDataAccessObject;

    @Before
    public void setUp() throws IOException {
        // Create mock objects for the models and the controllers
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        restaurantController = Mockito.mock(RestaurantController.class);
        restaurantViewModel = Mockito.mock(RestaurantViewModel.class);
        viewRestaurantController = Mockito.mock(ViewRestaurantController.class);
        addReviewController = Mockito.mock(AddReviewController.class);
        addToFavouritesController = Mockito.mock(AddToFavouritesController.class);
        addToFavouritesViewModel = Mockito.mock(AddToFavouritesViewModel.class);
        viewFavouritesController = Mockito.mock(ViewFavouritesController.class);
        addReviewViewModel = Mockito.mock(AddReviewViewModel.class);
        restaurantDataAccessObject = new APIRestaurantDataAccessObject();
        favouriteDataAccessObject = new FileFavouritesDataAccessObject("favourites.csv");

        // Create the view object with the mock objects as parameters
        restaurantView = new RestaurantView(restaurantViewModel, restaurantController,
                viewRestaurantController, addReviewController, addToFavouritesController,
                addToFavouritesViewModel, viewFavouritesController, viewManagerModel);
    }

    @Test
    public void testViewName() {
        assertEquals("Restaurant", restaurantView.viewName);
    }

    @Test
    public void testButtons() {
        assertTrue(restaurantView.addReview.isVisible());
        assertTrue(restaurantView.addToFavourite.isVisible());
        assertTrue(restaurantView.returnBack.isVisible());
    }

    @Test
    public void testAddToFavouriteButtonAction() {
        RestaurantState restaurantState = new RestaurantState();
        AddToFavouritesState favouriteState = new AddToFavouritesState();
        Mockito.when(restaurantViewModel.getState()).thenReturn(restaurantState);
        Mockito.when(addToFavouritesViewModel.getState()).thenReturn(favouriteState);
        Restaurant restaurant = restaurantDataAccessObject.getRestaurantByID("iGEvDk6hsizigmXhDKs2Vg");
        restaurantState.setRestaurant(restaurant);
        restaurantState.setUsername("Harry");
        restaurantState.setPassword("123");
        favouriteState.setUsername("Harry");
        favouriteState.setRestaurantName(restaurant.getRestaurantName());
        favouriteState.setMessage("Restaurant added to favourite list successfully");
        JButton addToFavouriteButton = restaurantView.addToFavourite;
        addToFavouriteButton.doClick();
        assertNotNull(favouriteDataAccessObject.getFavouritesList("Harry"));
    }

    @Test
    public void testAddReviewButtonAction() {
        RestaurantState restaurantState = new RestaurantState();
        AddReviewState reviewState = new AddReviewState();
        Mockito.when(restaurantViewModel.getState()).thenReturn(restaurantState);
        Mockito.when(addReviewViewModel.getState()).thenReturn(reviewState);
        Restaurant restaurant = restaurantDataAccessObject.getRestaurantByID("iGEvDk6hsizigmXhDKs2Vg");
        restaurantState.setRestaurant(restaurant);
        restaurantState.setUsername("Harry");
        restaurantState.setPassword("123");
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create("100001", "Harry", "123", "Toronto",
                LocalDateTime.now());
        reviewState.setUser(user);
        reviewState.setRestaurant(restaurant);
        reviewState.setPrevious_view("view restaurants");
        JButton addReviewButton = restaurantView.addReview;
        addReviewButton.doClick();
    }

    @Test
    public void testReturnButtonAction() {
        RestaurantState restaurantState = new RestaurantState();
        Restaurant restaurant = restaurantDataAccessObject.getRestaurantByID("iGEvDk6hsizigmXhDKs2Vg");
        restaurantState.setRestaurant(restaurant);
        restaurantState.setUserID("100001");
        restaurantState.setUsername("Harry");
        restaurantState.setPassword("123");
        Mockito.when(restaurantViewModel.getState()).thenReturn(restaurantState);
        // Simulate clicking the return button
        JButton returnButton = restaurantView.returnBack;
        returnButton.doClick();
    }
}
