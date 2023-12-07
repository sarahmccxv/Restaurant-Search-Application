package usecase_testing;

import api.yelp.YelpApiServices;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.register.*;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RegisterInteractorTest {

    private RegisterUserDataAccessInterface mockDataAccess;
    private RegisterOutputBoundary mockPresenter;
    private UserFactory mockUserFactory;
    private YelpApiServices mockApiServices;
    private RegisterInteractor registerInteractor;

    @BeforeEach
    public void setup() {
        mockDataAccess = mock(RegisterUserDataAccessInterface.class);
        mockPresenter = mock(RegisterOutputBoundary.class);
        mockUserFactory = mock(UserFactory.class);
        mockApiServices = mock(YelpApiServices.class);

        registerInteractor = new RegisterInteractor(mockDataAccess, mockApiServices, mockPresenter, mockUserFactory);
    }

     @Test
    public void testExecute_NewUser_Success() {
        // Prepare test data
        String username = "testUser";
        String password = "testPassword";
        String location = "testLocation";
        String repeatPassword = "testPassword";
        String userID = "123";
        LocalDateTime now = LocalDateTime.now();
        User newUser = new User(userID, username, password, location, now);

        // Mock behavior for DataAccessObject and ApiServices
        when(mockDataAccess.existsByName(username)).thenReturn(false);
        when(mockDataAccess.duplicatedID(anyInt())).thenReturn(false);
        when(mockApiServices.getLocalRestaurants(location)).thenReturn(null);
        when(mockUserFactory.create(anyString(), anyString(), anyString(), anyString(), any(LocalDateTime.class))).thenReturn(newUser);

        // Create input data
        RegisterInputData input = new RegisterInputData(username, password, repeatPassword, location);

        // Execute the interactor
        registerInteractor.execute(input);

        // Verify DataAccessObject methods were called
        verify(mockDataAccess).existsByName(username);
        verify(mockDataAccess).duplicatedID(anyInt());
        verify(mockDataAccess).save(newUser); // Verify save is called with the expected User object

        // Verify presenter method was called with expected output data
        ArgumentCaptor<RegisterOutputData> outputCaptor = ArgumentCaptor.forClass(RegisterOutputData.class);
        verify(mockPresenter).prepareSuccessView(outputCaptor.capture());

        RegisterOutputData capturedOutput = outputCaptor.getValue();
        assertEquals(username, capturedOutput.getUsername());
    }

}
