package usecase_testing;

import data_access.APIRestaurantDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.user_profile.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserProfileInteractorTest {

    private UserProfileOutputBoundary mockPresenter;
    private UserProfileDataAccessInterface mockDataAccess;
    private UserFactory mockUserFactory;
    private APIRestaurantDataAccessObject mockRestaurantDataAccess;
    private UserProfileInteractor userProfileInteractor;

    @BeforeEach
    public void setup() {
        mockPresenter = mock(UserProfileOutputBoundary.class);
        mockDataAccess = mock(UserProfileDataAccessInterface.class);
        mockUserFactory = mock(UserFactory.class);
        mockRestaurantDataAccess = mock(APIRestaurantDataAccessObject.class);

        userProfileInteractor = new UserProfileInteractor(mockPresenter, mockDataAccess,
                mockRestaurantDataAccess, mockUserFactory);
    }

    @Test
    public void testExecute_UpdateUserInfo_Success() {
        // Prepare test data
        String userID = "123";
        String username = "testUser";
        String password = "testPassword";
        String location = "testLocation";
        LocalDateTime creationTime = LocalDateTime.now();
        User oldUser = new User(userID, username, "oldPassword", "oldLocation", creationTime);

        // Mock behavior for DataAccessObject and UserFactory
        when(mockDataAccess.getByUserID(userID)).thenReturn(oldUser);
        when(mockUserFactory.create(userID, username, password, location, oldUser.getCreationTime()))
                .thenReturn(new User(userID, username, password, location, creationTime));

        // Create input data
        UserProfileInputData input = new UserProfileInputData(userID, username, password, location);

        // Execute the interactor
        userProfileInteractor.execute(input);

        // Verify DataAccessObject methods were called
        verify(mockDataAccess, times(1)).update();
        verify(mockDataAccess, times(1)).updateUserInfo(any(User.class));

        // Verify presenter method was called with expected output data
        ArgumentCaptor<UserProfileOutputData> outputCaptor = ArgumentCaptor.forClass(UserProfileOutputData.class);
        verify(mockPresenter).prepareSuccessView(outputCaptor.capture());

        UserProfileOutputData capturedOutput = outputCaptor.getValue();
        assertEquals(userID, capturedOutput.getUserID());
        assertEquals(username, capturedOutput.getUsername());
        assertEquals(password, capturedOutput.getPassword());
        assertEquals(location, capturedOutput.getLocation());
    }
}
