package view;

import data_access.APIRestaurantDataAccessObject;
import entity.Restaurant;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_review.AddReviewState;
import interface_adapter.add_review.AddReviewViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.write_review.WriteReviewController;
import interface_adapter.write_review.WriteReviewState;
import interface_adapter.write_review.WriteReviewViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class WriteReviewViewTest {
    private WriteReviewView writeReviewView;
    private ViewManagerModel viewManagerModel;
    private AddReviewViewModel addReviewViewModel;
    private WriteReviewViewModel writeReviewViewModel;
    private WriteReviewController writeReviewController;
    private RestaurantController restaurantController;

    @Before
    public void setUp() {
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        writeReviewViewModel = Mockito.mock(WriteReviewViewModel.class);
        writeReviewController = Mockito.mock(WriteReviewController.class);
        addReviewViewModel = Mockito.mock(AddReviewViewModel.class);
        restaurantController = Mockito.mock(RestaurantController.class);

        writeReviewView = new WriteReviewView(addReviewViewModel, writeReviewViewModel,
                writeReviewController, restaurantController);
    }

    @Test
    public void testViewName() {assertEquals("Add Review", writeReviewView.viewName);}

    @Test
    public void testButtons() {
        assertTrue(writeReviewView.ratingInputField.isEditable());
        assertTrue(writeReviewView.contentInputField.isEditable());
        assertTrue(writeReviewView.save.isVisible());
        assertTrue(writeReviewView.returnBack.isVisible());
    }

    @Test
    public void testSaveButtonAction() {
        APIRestaurantDataAccessObject restaurantDAO = new APIRestaurantDataAccessObject();
        Restaurant restaurant = restaurantDAO.getRestaurantByID("iGEvDk6hsizigmXhDKs2Vg");
        User user = new User("100001", "harry", "123",
                "Toronto", LocalDateTime.now());
        String rating = "5";
        String content = "good";
        WriteReviewState writeReviewState = new WriteReviewState();
        AddReviewState addReviewState = new AddReviewState();
        Mockito.when(writeReviewViewModel.getState()).thenReturn(writeReviewState);
        Mockito.when(addReviewViewModel.getState()).thenReturn(addReviewState);

        writeReviewState.setRating("5");
        writeReviewState.setContent("good");
        writeReviewState.setUser(user);
        writeReviewState.setRestaurant(restaurant);
        writeReviewState.setMessage("Review successfully added.");

        addReviewState.setUser(user);
        addReviewState.setRestaurant(restaurant);

        JButton saveButton = writeReviewView.save;
        saveButton.doClick();
        verify(writeReviewController).execute(user, restaurant, rating, content);
    }
}
