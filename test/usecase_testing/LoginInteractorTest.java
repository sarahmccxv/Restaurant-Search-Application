package usecase_testing;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.login.*;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

public class LoginInteractorTest {

    private LoginUserDataAccessInterface mockDataAccess;
    private LoginOutputBoundary mockPresenter;
    private LoginInteractor loginInteractor;
    LocalDateTime creationTime = LocalDateTime.now();

    @BeforeEach
    public void setup() {
        mockDataAccess = mock(LoginUserDataAccessInterface.class);
        mockPresenter = mock(LoginOutputBoundary.class);

        loginInteractor = new LoginInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    public void testExecute_ExistingUser_CorrectPassword_Success() {
        // Prepare test data
        String username = "testUser";
        String password = "testPassword";
        User existingUser = new User("123", username, password, "Test Location", creationTime);

        // Mock behavior for DataAccessObject
        when(mockDataAccess.existsByName(username)).thenReturn(true);
        when(mockDataAccess.getByUsername(username)).thenReturn(existingUser);

        // Create input data
        LoginInputData input = new LoginInputData(username, password);

        // Execute the interactor
        loginInteractor.execute(input);

        // Verify presenter method was called with expected output data
        ArgumentCaptor<LoginOutputData> outputCaptor = ArgumentCaptor.forClass(LoginOutputData.class);
        verify(mockPresenter).prepareSuccessView(outputCaptor.capture());

        LoginOutputData capturedOutput = outputCaptor.getValue();
        assertEquals(existingUser.getUserID(), capturedOutput.getUserID());
        assertEquals(existingUser.getUsername(), capturedOutput.getUsername());
        assertEquals(existingUser.getPassword(), capturedOutput.getPassword());
        assertEquals(existingUser.getLocation(), capturedOutput.getLocation());
    }

    private void assertEquals(String userID, String userID1) {
    }

    @Test
    public void testExecute_NonExistingUser_Failure() {
        // Prepare test data
        String username = "nonExistingUser";

        // Mock behavior for DataAccessObject
        when(mockDataAccess.existsByName(username)).thenReturn(false);

        // Create input data
        LoginInputData input = new LoginInputData(username, "somePassword");

        // Execute the interactor
        loginInteractor.execute(input);

        // Verify presenter method was called with failure message
        verify(mockPresenter).prepareFailView(username + ": Account does not exist.");
    }

    @Test
    public void testExecute_ExistingUser_IncorrectPassword_Failure() {
        // Prepare test data
        String username = "testUser";
        String correctPassword = "testPassword";
        String incorrectPassword = "wrongPassword";

        // Mock behavior for DataAccessObject
        when(mockDataAccess.existsByName(username)).thenReturn(true);
        when(mockDataAccess.getByUsername(username)).thenReturn(new User("123", username, correctPassword, "Test Location", creationTime));

        // Create input data with incorrect password
        LoginInputData input = new LoginInputData(username, incorrectPassword);

        // Execute the interactor
        loginInteractor.execute(input);

        // Verify presenter method was called with failure message
        verify(mockPresenter).prepareFailView("Incorrect password for " + username + ".");
    }
}
