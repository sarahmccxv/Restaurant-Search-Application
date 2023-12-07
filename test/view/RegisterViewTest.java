package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.register.RegisterController;
import interface_adapter.register.RegisterState;
import interface_adapter.register.RegisterViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class RegisterViewTest {
    private RegisterView registerView;
    private ViewManagerModel viewManagerModel;
    private RegisterViewModel registerViewModel;
    private RegisterController registerController;

    @Before
    public void setUp() {
        viewManagerModel = Mockito.mock(ViewManagerModel.class);
        registerViewModel = Mockito.mock(RegisterViewModel.class);
        registerController = Mockito.mock(RegisterController.class);

        registerView = new RegisterView(registerController, registerViewModel, viewManagerModel);
    }

    @Test
    public void testViewName() {assertEquals("sign up", registerView.viewName);}

    @Test
    public void testButtons() {
        // Check that all buttons and input fields are functioning
        assertTrue(registerView.register.isVisible());
        assertTrue(registerView.login.isVisible());
        assertTrue(registerView.locationInputField.isEditable());
        assertTrue(registerView.passwordInputField.isEditable());
        assertTrue(registerView.repeatPasswordInputField.isEditable());
        assertTrue(registerView.locationInputField.isEditable());
    }

    @Test
    public void testRegisterButtonAction() {
        RegisterState state = new RegisterState();
        Mockito.when(registerViewModel.getState()).thenReturn(state);

        state.setUsername("Harry");
        state.setPassword("123");
        state.setRepeatPassword("123");
        state.setLocation("Toronto");

        JButton registerButton = registerView.register;
        registerButton.doClick();

        verify(registerController).execute("Harry", "123", "123", "Toronto");
    }

    @Test
    public void testLoginButtonAction() {
        JButton loginButton = registerView.login;
        loginButton.doClick();
    }

}
