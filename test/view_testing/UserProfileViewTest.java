package view_testing;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import view.UserProfileView;

import javax.swing.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class UserProfileViewTest {

    private UserProfileView userProfileView;
    private ViewManagerModel viewManagerModel;
    private UserProfileController userProfileController;
    private UserProfileViewModel userProfileViewModel;
    private LoginController loginController;

    @Before
    public void setUp() {
        // Create mock objects for the models and the controllers
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        userProfileController = Mockito.mock(UserProfileController.class);
        userProfileViewModel = Mockito.mock(UserProfileViewModel.class);
        loginController = Mockito.mock(LoginController.class);

        // Create the view object with the mock objects as parameters
        userProfileView = new UserProfileView(viewManagerModel, userProfileViewModel, userProfileController, loginController);
    }

    @Test
    public void testViewName() {
        // Check that the view name is correct
        assertEquals("user profile", userProfileView.viewName);
    }

    @Test
    public void testEditButtonAction() {
        // Simulate clicking the edit button
        JButton editButton = userProfileView.editButton;
        editButton.doClick();

        // Check that the input fields are editable and the buttons are visible
        assertTrue(userProfileView.passwordInputField.isEditable());
        assertTrue(userProfileView.locationInputField.isEditable());
        assertTrue(userProfileView.saveButton.isVisible());
        assertTrue(userProfileView.cancelButton.isVisible());
        assertFalse(userProfileView.editButton.isVisible());
        assertFalse(userProfileView.returnButton.isVisible());
    }

    @Test
    public void testSaveButtonAction() {
        // Set the state of the view model
        UserProfileState state = new UserProfileState();
        Mockito.when(userProfileViewModel.getState()).thenReturn(state);

        // Simulate changing the password and the location in the input fields
        userProfileView.passwordInputField.setText("newPass");
        userProfileView.locationInputField.setText("New York");

        // Simulate clicking the save button
        JButton saveButton = userProfileView.saveButton;
        saveButton.doClick();
    }

    @Test
    public void testCancelButtonAction() {
        // Set the state of the view model
        UserProfileState state = new UserProfileState();
        state.setPassword("pass");
        state.setLocation("Toronto");
        Mockito.when(userProfileViewModel.getState()).thenReturn(state);

        // Simulate changing the password and the location in the input fields
        userProfileView.passwordInputField.setText("newPass");
        userProfileView.locationInputField.setText("New York");

        // Simulate clicking the cancel button
        JButton cancelButton = userProfileView.cancelButton;
        cancelButton.doClick();

        // Check that the input fields are reset to the original state and not editable
        assertEquals("pass", userProfileView.passwordInputField.getText());
        assertEquals("Toronto", userProfileView.locationInputField.getText());
        assertFalse(userProfileView.passwordInputField.isEditable());
        assertFalse(userProfileView.locationInputField.isEditable());
    }

    @Test
    public void testReturnButtonAction() {
        UserProfileState state = new UserProfileState();
        state.setUsername("James");
        state.setPassword("pass");
        Mockito.when(userProfileViewModel.getState()).thenReturn(state);
        // Simulate clicking the return button
        JButton returnButton = userProfileView.returnButton;
        returnButton.doClick();

        verify(loginController).execute("James", "pass");

    }
}
